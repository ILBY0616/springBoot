package wareManage.mapper;

import org.apache.ibatis.annotations.Mapper;
import wareManage.entity.Ware;

import java.util.List;

@Mapper
public interface WareMapper {
    int addWare(Ware ware);

    int deleteWareById(int id);

    int updateWareById(Ware ware);

    List<Ware> findAllWare();

    List<Ware> findWareByAttribute(Ware ware);
}
