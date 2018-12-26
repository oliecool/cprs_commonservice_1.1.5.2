package cprs_advertisement_service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class testJson {
String dd="{ \"110000\": { \"name\": \"北京市\", \"child\": { \"110100\": { \"name\": \"市辖区\", \"child\": { \"110101\": \"东城区\", \"110102\": \"西城区\", \"110105\": \"朝阳区\", \"110106\": \"丰台区\", \"110107\": \"石景山区\", \"110108\": \"海淀区\", \"110109\": \"门头沟区\", \"110111\": \"房山区\", \"110112\": \"通州区\", \"110113\": \"顺义区\", \"110114\": \"昌平区\", \"110115\": \"大兴区\", \"110116\": \"怀柔区\", \"110117\": \"平谷区\", \"110118\": \"密云区\", \"110119\": \"延庆区\" } } } },\"120000\": { \"name\": \"天津市\", \"child\": { \"120100\": { \"name\": \"市辖区\", \"child\": { \"120101\": \"和平区\", \"120102\": \"河东区\", \"120103\": \"河西区\", \"120104\": \"南开区\", \"120105\": \"河北区\", \"120106\": \"红桥区\", \"120110\": \"东丽区\", \"120111\": \"西青区\", \"120112\": \"津南区\", \"120113\": \"北辰区\", \"120114\": \"武清区\", \"120115\": \"宝坻区\", \"120116\": \"滨海新区\", \"120117\": \"宁河区\", \"120118\": \"静海区\", \"120119\": \"蓟州区\" } } } }}";

    @Test
    public void test() {
        
        JSONArray d= JSON.parseArray(dd);
        Object[] dd = d.toArray();
        List ddlist =new ArrayList<Object>();
        ddlist = Arrays.asList(dd);
       for (int i = 0; i < ddlist.size(); i++) {
           System.out.print(ddlist.get(i));
       }
        
    }

}
