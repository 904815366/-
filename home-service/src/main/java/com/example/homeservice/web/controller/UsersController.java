package com.example.homeservice.web.controller;

import com.example.homeservice.config.IdempotentToken;
import com.example.homeservice.dao.po.RolePo;
import com.example.homeservice.dao.po.UsersPo;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        List<UsersDto> dtos = usersConverter.from(page.getContent());
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
                                        AddUsersFo addUsersFo) {
        log.info("进入addUsers方法");
        UsersPo exist = usersRepository.findByUsername(addUsersFo.getUsername()).orElse(null);
        if (!ObjectUtils.isEmpty(exist)) {
            throw new RuntimeException("添加用户失败,用户名已存在.");
        }
        upfile(files, addUsersFo);
        usersService.addUser(addUsersFo);
        log.info("usersService.addUser执行成功");
        return new ResponseResult<>(200, "职员添加成功", null);
    }


    /**
     * 修改user
     * @param files 头像
     * @param fo 基本信息
     * @return
     */
    @PutMapping("/users")
    public ResponseResult<Void> modifyUser(@RequestParam("uploadFile") List<MultipartFile> files,
                                           AddUsersFo fo) {
        log.info("进入modifyUser方法");
        System.out.println(fo);
        upfile(files, fo);
        UsersPo usersPo = UsersPo.builder().id(fo.getId()).username(fo.getUsername())
                .password(fo.getPassword()).name(fo.getName()).tel(fo.getTel())
                .email(fo.getEmail()).role(RolePo.builder().id(fo.getRoleid()).build())
                .status(fo.getStatus()).avatar(fo.getAvatar()).version(fo.getVersion()).build();
        boolean modifyResult = usersService.modifyUser(usersPo);
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


    @DeleteMapping("/users")
    public ResponseResult<Void> removeUser(Long id,String status){
        UsersPo po = usersRepository.findByIdAndStatusNot(id,status).orElseThrow(() -> new NullPointerException("删除失败"));
        po.setStatus("2");
        usersService.modifyStatus(po);
        return new ResponseResult<>(200,"删除成功",null);
    }


}
