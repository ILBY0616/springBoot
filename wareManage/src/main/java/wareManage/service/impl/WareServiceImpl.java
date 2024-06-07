package wareManage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wareManage.entity.Ware;
import wareManage.mapper.WareMapper;
import wareManage.service.WareService;

import java.util.List;

@Service
public class WareServiceImpl implements WareService {

    final WareMapper wareMapper;

    @Autowired
    public WareServiceImpl(WareMapper wareMapper) {
        this.wareMapper = wareMapper;
    }


    @Override
    public int addWare(Ware ware) {
        List<Ware> wareList = wareMapper.findWareByAttribute(ware);
        if (wareList.isEmpty()) {
            return wareMapper.addWare(ware);
        } else {
            return 0;
        }
    }

    @Override
    public int deleteWareById(int id) {
        return wareMapper.deleteWareById(id);
    }

    @Override
    public int updateWareById(Ware ware) {
        return wareMapper.updateWareById(ware);
    }

    @Override
    public List<Ware> findAllWare() {
        return wareMapper.findAllWare();
    }

    @Override
    public List<Ware> findWareByAttribute(String attribute, String value) {
        Ware ware = new Ware();
        switch (attribute) {
            case "id":
                ware.setId(Integer.parseInt(value));
                break;
            case "name":
                ware.setName(value);
                break;
            case "brand":
                ware.setBrand(value);
                break;
            case "category":
                ware.setCategory(value);
                break;
            case "price":
                ware.setPrice(Double.parseDouble(value));
                break;
            case "stock":
                ware.setStock(Integer.parseInt(value));
                break;
            case "picAddress":
                ware.setPicAddress(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid Attribute: " + attribute);
        }
        return wareMapper.findWareByAttribute(ware);
    }
}
