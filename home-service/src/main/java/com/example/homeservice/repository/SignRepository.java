package com.example.homeservice.repository;


import com.example.homeservice.dao.mysql.SignDao;
import com.example.homeservice.dao.po.SignPo;
import com.example.homeservice.web.converter.SignConverter;
import com.example.homeservice.web.dto.PageDto;
import com.example.homeservice.web.dto.SignsDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Repository
public class SignRepository {
    @Resource
    private SignDao signDao;
    @Resource
    private SignConverter signConverter;

    public List<SignPo> findAllByTodaytime(String todaytime) {
       return signDao.findAllByTodaytimeEquals(todaytime);
    }

    public void addSigns(List<SignPo> signPoList) {
        signDao.saveAll(signPoList);
    }

    public Optional<SignPo> findByUsername( SignPo signPo) {
        Example<SignPo> example = Example.of(signPo);
        return signDao.findOne(example);
    }

    public void modifyStatus(SignPo signPo) {
        signDao.save(signPo);
    }

    public PageDto<SignsDto> findAll(Example<SignPo> example, Pageable pageable){

        Page<SignPo> poPage = signDao.findAll(example, pageable);
        List<SignPo> content = poPage.getContent();
        for (SignPo po : content) {
            System.out.println(po);
        }
        PageDto<SignsDto> pageDto = new PageDto<>();
        List<SignsDto> dtos = signConverter.from(content);

        return pageDto.getPageDto(poPage, dtos);

    }
}
