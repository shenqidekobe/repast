import com.yiyou.repast.merchant.model.GoodsSpec;
import com.yiyou.repast.merchant.service.impl.GoodsSpecServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class test {


    @Test
    public void ccc(){
        List<Long> list =new ArrayList<>();
        list.add(1L);
        list.add(9L);
        list.add(10L);

        GoodsSpecServiceImpl service =new GoodsSpecServiceImpl();
        Set<GoodsSpec> byIds = service.findByIds(list);

        System.out.println("size :"+byIds);
    }
}
