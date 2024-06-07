package wareManage.service;

import wareManage.entity.Ware;

import java.util.List;

public interface WareService {

    int addWare(Ware ware);

    int deleteWareById(int id);

    int updateWareById(Ware ware);

    List<Ware> findAllWare();

    List<Ware> findWareByAttribute(String attribute, String value);

}
