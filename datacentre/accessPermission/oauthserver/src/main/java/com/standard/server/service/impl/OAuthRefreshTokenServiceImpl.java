package com.standard.server.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.orm.componet.feature.DynamicDelete;
import com.standard.orm.componet.feature.DynamicTypeSelect;
import com.standard.server.entitiy.OAuthRefreshToken;
import com.standard.server.service.OAuthRefreshTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class OAuthRefreshTokenServiceImpl extends BaseServiceImpl<OAuthRefreshToken, Long> implements OAuthRefreshTokenService {

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public int  deleteByTokenId(String tokenId) {
      DynamicDelete dynamicDelete = baseRepository.getDynamicDelete();
      dynamicDelete.dynamicBuild(ele ->{
              Predicate predicate = ele.flat.addEq("tokenId",tokenId, JoinType.LEFT).and();
              ele.delete.where(predicate);
             return predicate;
          });
        return dynamicDelete.getResult();
    }
    @Override
    public List<OAuthRefreshToken> findByTokenId(String tokenId){
      DynamicTypeSelect dynamicTypeSelect = baseRepository.getDynamicTypeSelect();
      dynamicTypeSelect.dynamicBuild(ele->{
          Predicate predicate = ele.flat.addEq("tokenId",tokenId, JoinType.LEFT).and();
          ele.query.where(predicate);
            return predicate;
        });
      return  dynamicTypeSelect.getResult();
    }

}