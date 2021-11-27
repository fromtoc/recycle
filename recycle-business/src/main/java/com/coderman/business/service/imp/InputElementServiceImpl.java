package com.coderman.business.service.imp;

import com.coderman.business.mapper.InputElementMapper;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProductPriceMapper;
import com.coderman.business.service.InputElementService;
import com.coderman.business.service.ProductCategoryService;
import com.coderman.business.service.ProductPriceService;
import com.coderman.common.model.business.InputElement;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.ProductPrice;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.business.ProductPriceUploadVO;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:19
 * @Version 1.0
 **/
@Service
@Transactional
public class InputElementServiceImpl implements InputElementService {

    @Autowired
    private InputElementMapper inputElementMapper;

    @Override
    public int add(List<InputElement> inputElements) {
        return 0;
    }
}
