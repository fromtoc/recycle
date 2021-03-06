package com.coderman.system.service.impl;

import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Menu;
import com.coderman.common.utils.MenuTreeBuilder;
import com.coderman.common.vo.business.ProductCategoryExportVO;
import com.coderman.common.vo.system.MenuExportVO;
import com.coderman.common.vo.system.MenuNodeVO;
import com.coderman.common.vo.system.MenuVO;
import com.coderman.system.converter.MenuConverter;
import com.coderman.system.mapper.MenuMapper;
import com.coderman.system.mapper.RoleMapper;
import com.coderman.system.mapper.RoleMenuMapper;
import com.coderman.system.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/10 11:56
 * @Version 1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 加载選單树（按钮和選單）
     *
     * @return
     */
    @Override
    public List<MenuNodeVO> findMenuTree() {
        List<Menu> menus = menuMapper.selectAll();
        List<MenuNodeVO> menuNodeVOS = MenuConverter.converterToALLMenuNodeVO(menus);
        return MenuTreeBuilder.build(menuNodeVOS);
    }

    /**
     * 新增選單
     *
     * @param menuVO
     */
    @Override
    public Menu add(MenuVO menuVO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO, menu);
        menu.setCreateTime(new Date());
        menu.setModifiedTime(new Date());
        menu.setAvailable(menuVO.isDisabled() ? 0 : 1);
        menu.setLoadTime(new Date());
        menuMapper.insert(menu);
        return menu;
    }

    /**
     * 删除選單
     *
     * @param id
     */
    @Override
    public void delete(Long id) throws SystemException {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if (menu == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要删除的選單不存在");
        }
        menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 編辑選單
     *
     * @param id
     * @return
     */
    @Override
    public MenuVO edit(Long id) throws SystemException {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if (menu == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該編辑的選單不存在");
        }
        return MenuConverter.converterToMenuVO(menu);
    }

    /**
     * 更新選單
     *
     * @param id
     * @param menuVO
     */
    @Override
    public void update(Long id, MenuVO menuVO) throws SystemException {
        Menu dbMenu = menuMapper.selectByPrimaryKey(id);
        if (dbMenu == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要更新的選單不存在");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO, menu);
        menu.setId(id);
        menu.setAvailable(menuVO.isDisabled() ? 0 : 1);
        menu.setModifiedTime(new Date());
        menu.setLoadTime(new Date());
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 获取展开项
     *
     * @return
     */
    @Override
    public List<Long> findOpenIds() {
        List<Long> ids = new ArrayList<>();
        List<Menu> menus = menuMapper.selectAll();
        if (!CollectionUtils.isEmpty(menus)) {
            for (Menu menu : menus) {
                if (menu.getOpen() == 1) {
                    ids.add(menu.getId());
                }
            }
        }
        return ids;
    }


    /**
     * 获取所有選單
     *
     * @return
     */
    @Override
    public List<MenuExportVO> findAll() {
        List<Menu> menus = menuMapper.selectAll();
        List<MenuExportVO> voList = new ArrayList<>();
        menus.stream().forEach(m -> {
            MenuExportVO vo = new MenuExportVO();
            BeanUtils.copyProperties(m, vo);
            if (m.getUrl() != null && !"".equals(m.getUrl())) {
                vo.setFirstLevelName(menuMapper.selectByPrimaryKey(m.getParentId()).getMenuName());
                vo.setSecondLevelName(m.getMenuName());
                vo.setThirdLevelName("");
                vo.setLevel("二級選單");
            } else {
                if (m.getParentId() == 0) {
                    vo.setFirstLevelName(vo.getMenuName());
                    vo.setSecondLevelName("");
                    vo.setThirdLevelName("");
                    vo.setLevel("一級選單");
                } else {
                    Long secondId = m.getParentId();
                    Menu secondMenu = menuMapper.selectByPrimaryKey(secondId);
                    Long firstId = secondMenu.getParentId();
                    vo.setFirstLevelName(menuMapper.selectByPrimaryKey(firstId).getMenuName());
                    vo.setSecondLevelName(secondMenu.getMenuName());
                    vo.setThirdLevelName(m.getMenuName());
                    vo.setLevel("三級按鈕");
                }
            }
            voList.add(vo);
        });
        return voList;
    }


}
