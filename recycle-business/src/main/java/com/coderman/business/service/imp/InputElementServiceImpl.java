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
    public int batchAdd(List<InputElement> inputElementList) {
        int count = 0;
        for (InputElement inputElement: inputElementList) {
            Long itemId = inputElement.getItemId();
            String validMonth = inputElement.getValidMonth();
            inputElement.setLoadTime(new Date());
            int i = inputElementMapper.insert(inputElement);
            count = count + i;
        }
        return count;
    }

    @Override
    public List<InputElement> checkSame(List<InputElement> inputElementList) {
        List<InputElement> sameList = new ArrayList<>();
        inputElementList.stream().forEach(upload-> {
            Example o = new Example(InputElement.class);
            Example.Criteria criteria = o.createCriteria();
            criteria.andEqualTo("itemId", upload.getItemId());
            criteria.andEqualTo("validMonth", upload.getValidMonth());
            List<InputElement> i = inputElementMapper.selectByExample(o);
            if (!CollectionUtils.isEmpty(i)) {
                sameList.add(i.get(0));
            }
        });
        return sameList;
    }

    @Override
    public int recover(List<InputElement> inputElementList) {
        int count = 0;
        for (InputElement p: inputElementList) {
            Example o = new Example(InputElement.class);
            Example.Criteria criteria = o.createCriteria();
            criteria.andEqualTo("itemId", p.getItemId());
            criteria.andEqualTo("validMonth", p.getValidMonth());
            inputElementMapper.deleteByExample(o);
            p.setLoadTime(new Date());
            int i = inputElementMapper.insert(p);
            count = count + i;
        }
        return count;
    }
}
