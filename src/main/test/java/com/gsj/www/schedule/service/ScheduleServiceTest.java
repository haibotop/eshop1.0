package java.com.gsj.www.schedule.service;

import com.gsj.www.common.util.DateProvider;
import com.gsj.www.purchase.domain.PurchaseOrderDTO;
import com.gsj.www.purchase.domain.PurchaseOrderItemDTO;
import com.gsj.www.schedule.service.ScheduleService;
import com.gsj.www.wms.domain.PurchaseInputOrderDTO;
import com.gsj.www.wms.domain.PurchaseInputOrderItemDTO;
import com.gsj.www.wms.service.WmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * 调度中心对外接口sercice组件的单元测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {
    /**
     * 调度中心service组件
     */
    @Autowired
    private ScheduleService scheduleService;
    /**
     * 日期辅助组件
     */
    @Autowired
    private DateProvider dateProvider;
    /**
     * wms中心service组件
     */
    @MockBean
    private WmsService wmsService;

    /**
     * 测试调度采购入库
     * @throws Exception
     */
    @Test
    public void testSchedulePurchaseInput() throws Exception{
        PurchaseOrderDTO purchaseOrder = createPurchaseOrder();
        PurchaseInputOrderDTO purchaseInputOrder = createPurchaseInputOrder();
        scheduleService.schedulePurchaseInput(purchaseOrder);

        verify(wmsService, times(1)).createPurchaseInputOrder(purchaseInputOrder);
    }

    /**
     * 创建采购单
     * @return 采购单
     */
    private PurchaseOrderDTO createPurchaseOrder() throws Exception{
        Long purchaseOrderId = 1L;

        PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();
        purchaseOrder.setId(purchaseOrderId);
        purchaseOrder.setSupplierId(1L);
        purchaseOrder.setExpectArrivalTime(dateProvider.parseDatetime("2019-10-17 10:00:00"));
        purchaseOrder.setContactor("张三");
        purchaseOrder.setContactEmail("测试邮箱");
        purchaseOrder.setRemark("测试采购单");
        purchaseOrder.setPurchaser("李四");
        purchaseOrder.setStatus(3);
        purchaseOrder.setGmtCreate(dateProvider.getCurrentTime());
        purchaseOrder.setGmtModified(dateProvider.getCurrentTime());

        List<PurchaseOrderItemDTO> items = new ArrayList<PurchaseOrderItemDTO>();
        for (int i = 0; i < 5; i++){
            items.add(createPurchaseOrderItem(purchaseOrderId,(long)i,(long)i));
        }
        purchaseOrder.setItems(items);
        return purchaseOrder;
    }

    /**
     * 创建采购单条目
     * @param purchaseOrderId
     * @param i
     * @param i1
     * @return 采购单条目
     */
    private PurchaseOrderItemDTO createPurchaseOrderItem(Long purchaseOrderId, long itemId, long goodsSkuId) throws  Exception{
        PurchaseOrderItemDTO item = new PurchaseOrderItemDTO();
        item.setId(itemId);
        item.setPurchaseOrderId(purchaseOrderId);
        item.setGoodsSkuId(goodsSkuId);
        item.setPurchaseCount(1000L);
        item.setPurchasePrice(599.45);
        item.setGmtCreate(dateProvider.getCurrentTime());
        item.setGmtModified(dateProvider.getCurrentTime());
        return item;
    }

    /**
     * 创建采购入库单
     * @return
     */
    private PurchaseInputOrderDTO createPurchaseInputOrder() throws Exception {
        PurchaseInputOrderDTO purchaseInputOrder = new PurchaseInputOrderDTO();
        purchaseInputOrder.setSupplierId(1L);
        purchaseInputOrder.setExpectArrivalTime(dateProvider.parseDatetime("2019-10-18 12:30:00"));
        purchaseInputOrder.setPurchaseContactor("张三");
        purchaseInputOrder.setPurchaseContactPhoneNumber("测试电话");
        purchaseInputOrder.setPurchaseContactEmail("测试邮箱");
        purchaseInputOrder.setPurchaseInputOrderComment("测试采购单");
        purchaseInputOrder.setPurchaser("李四");

        List<PurchaseInputOrderItemDTO> items = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            items.add(createPurchaseInputOrderItem((long)i));
        }
        purchaseInputOrder.setPurchaseInputOrderItemDTOS(items);

        return purchaseInputOrder;
    }

    /**
     * 创建采购入库单条目
     * @param i 采购单条目
     * @return
     */
    private PurchaseInputOrderItemDTO createPurchaseInputOrderItem(long goodsSkuId) throws Exception{
        PurchaseInputOrderItemDTO item = new PurchaseInputOrderItemDTO();
        item.setGoodsSkuId(goodsSkuId);
        item.setPurchaseCount(1000L);
        item.setPurchasePrice(599.45);
        return item;
    }


}
