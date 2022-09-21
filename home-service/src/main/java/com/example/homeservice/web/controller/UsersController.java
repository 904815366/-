package com.example.homeservice.web.controller;

import com.example.homeservice.config.IdempotentToken;
import com.example.homeservice.dao.mysql.po.RolePo;
import com.example.homeservice.dao.mysql.po.UsersPo;
import com.example.homeservice.repository.UsersRepository;
import com.example.homeservice.service.UsersService;
import com.example.homeservice.utils.MinioUtils;
import com.example.homeservice.utils.ResponseResult;
import com.example.homeservice.web.converter.UsersConverter;
import com.example.homeservice.web.dto.PageDto;
import com.example.homeservice.web.dto.UsersDto;
import com.example.homeservice.web.fo.AddUsersFo;
import com.example.homeservice.web.fo.AnewPasswordFo;
import com.example.homeservice.web.fo.QueryUsersFo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class UsersController {

    @Resource
    private UsersRepository usersRepository;
    @Resource
    private UsersConverter usersConverter;
    @Resource
    private UsersService usersService;
    @Resource
    private MinioUtils minioUtils;



    /**
     * 修改密码,
     *
     * @param anewPasswordFo 用户名 旧密码 新密码
     * @return
     */
    @PutMapping("/users/password")
    public ResponseResult<Void> anewPassword(AnewPasswordFo anewPasswordFo) {
        log.info("进入anewPassword方法");
        return anewPasswordFo.exec();
    }

    /**
     * 动态条件 分页查询users列表
     *
     * @param queryUsersFo
     * @return
     */
    @GetMapping("/users")
    public ResponseResult<PageDto<UsersDto>> findUsers(QueryUsersFo queryUsersFo) {
        System.out.println(queryUsersFo);
        Page<UsersPo> page = usersRepository.findUsersByUsernameAndStatusAndCreatetime(queryUsersFo);
        List<UsersPo> collect = page.getContent().stream().filter(usersPo -> !usersPo.getUsername().equals("admin")).collect(Collectors.toList());
//        collect.forEach(System.out::println);
        List<UsersDto> dtos = usersConverter.from(collect);
        PageDto<UsersDto> pageDto = new PageDto<UsersDto>().getPageDto(page, dtos);
        return new ResponseResult<>(200, "success", pageDto);
    }


    /**
     * 新增users
     *
     * @param files      头像
     * @param addUsersFo 基本信息
     * @return
     * @throws Exception username已存在,新增失败
     */
    @PostMapping("/users")
    @IdempotentToken
    public ResponseResult<Void> addUser(@RequestParam("uploadFile") List<MultipartFile> files,
                                        AddUsersFo addUsersFo, HttpServletRequest request) {
        try {
            log.info("进入addUsers方法");
            UsersPo exist = usersRepository.findByUsername(addUsersFo.getUsername()).orElse(null);
            if (!ObjectUtils.isEmpty(exist)) {
                throw new RuntimeException("添加用户失败,用户名已存在.");
            }
            upfile(files, addUsersFo);
            usersService.addUser(addUsersFo, request);
            log.info("usersService.addUser执行成功");
            return new ResponseResult<>(200, "职员添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(200, "职员添加失败", null);
        }

    }


    /**
     * 修改user
     *
     * @param files 头像
     * @param fo    基本信息
     * @return
     */
    @PutMapping("/users")
    public ResponseResult<Void> modifyUser(@RequestParam("uploadFile") List<MultipartFile> files,
                                           AddUsersFo fo) {
        log.info("进入modifyUser方法");
        System.out.println(fo);
        if ("admin".equals(fo.getUsername())){
            return new ResponseResult<>(403, "修改失败,不允许修改该账户", null);
        }
        UsersPo po = usersRepository.findById(fo.getId()).orElseThrow(() -> new NullPointerException("不能修改ID"));
        upfile(files, fo);
        po.setUsername(fo.getUsername());
        po.setPassword(fo.getPassword());
        po.setName(fo.getName());
        po.setTel(fo.getTel());
        po.setEmail(fo.getEmail());
        po.setRole(RolePo.builder().id(fo.getRoleid()).build());
        po.setStatus(fo.getStatus());
        po.setAvatar(fo.getAvatar());
        po.setVersion(fo.getVersion());
        System.out.println(po);
        boolean modifyResult = usersService.modifyUser(po);
        if (modifyResult) return new ResponseResult<>(200, "修改成功", null);
        else return new ResponseResult<>(200, "修改失败,请稍后再试", null);
    }


    //文件上传
    private void upfile(List<MultipartFile> files, AddUsersFo addUsersFo) {
        try {
            log.info("进入upfile方法");
            System.out.println(files.size());

            for (int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);

                if (file.getOriginalFilename().equals(""))
                    return;

                log.info(file.getOriginalFilename());
                // 检查存储桶是否已经存在
                boolean isExist = minioUtils.doesBucketExists("users-avatar");
                if (isExist) {
                    System.out.println("Bucket already exists.");
                } else {
                    // 创建一个名为 asiatrip 的存储桶，用于存储文件。
                    log.info("create Bucket");
                    minioUtils.createBucket("users-avatar");
                }


                String filename = file.getOriginalFilename();
                String contentType = null;
                if (".jpg".equals(filename.substring(filename.lastIndexOf(".")))) {
                    contentType = "image/jpeg";
                } else {
                    contentType = "image/png";
                }

                String uuid = UUID.randomUUID() + contentType;

                // 使用 putObject 上传一个文件到存储桶中。
                minioUtils.putObject("users-avatar", file, uuid, contentType);
                String url = minioUtils.getObjectUrl("users-avatar", uuid, 7, TimeUnit.DAYS);
                addUsersFo.setAvatar(url);
                log.info("图片上传成功");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * 传入ID 和 状态[2 - 已注销]  逻辑删除用户
     *
     * @param id
     * @return
     */
<<<<<<< master
    @DeleteMapping("/users")
    public ResponseResult<Void> removeUser(Long id, String status) {
        UsersPo po = usersRepository.findByIdAndStatusNot(id, status).orElseThrow(() -> new NullPointerException("删除失败"));
        po.setStatus(status);
=======
    @DeleteMapping("/users/{id}")
    public ResponseResult<Void> removeUser(@PathVariable("id") Long id) {
        UsersPo po = usersService.findById(id);
        if ("admin".equals(po.getUsername())) return new ResponseResult<>(403, "删除失败,不允许删除该账户", null);
        if (!po.getStatus().equals("0")&&!po.getStatus().equals("1")&&!po.getStatus().equals("2")) return new ResponseResult<>(403, "状态输入有误", null);
        if (po.getStatus().equals("2"))  return new ResponseResult<>(403, "用户已注销", null);
        po.setStatus("2");
>>>>>>> [09/21 16:48 罗虎]  添加了modifySettlement
        usersService.modifyStatus(po);

        return new ResponseResult<>(200, "删除成功", null);
    }


    /**
     * 根据ID获取用户姓名   给资产微服务调用
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public ResponseResult<String> queryNameById(@PathVariable("id") Long id) {
        log.info("usersController : queryNameById 方法");
        System.out.println(id);
        UsersPo po = usersService.queryNameById(id);
        return new ResponseResult<>(200, "success", po.getName());
    }

}
