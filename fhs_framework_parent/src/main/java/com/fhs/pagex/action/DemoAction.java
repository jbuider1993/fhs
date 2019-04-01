package com.fhs.pagex.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webApi/pagex/demo")
public class DemoAction {

    @RequestMapping("tree")
    public JSONArray tree(){

        return JSON.parseArray("[{\n" +
                "\t\"id\":1,\n" +
                "\t\"text\":\"My Documents\",\n" +
                "\t\"children\":[{\n" +
                "\t\t\"id\":11,\n" +
                "\t\t\"text\":\"Photos\",\n" +
                "\t\t\"state\":\"closed\",\n" +
                "\t\t\"children\":[{\n" +
                "\t\t\t\"id\":111,\n" +
                "\t\t\t\"text\":\"Friend\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":112,\n" +
                "\t\t\t\"text\":\"Wife\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":113,\n" +
                "\t\t\t\"text\":\"Company\"\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"id\":12,\n" +
                "\t\t\"text\":\"Program Files\",\n" +
                "\t\t\"children\":[{\n" +
                "\t\t\t\"id\":121,\n" +
                "\t\t\t\"text\":\"Intel\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":122,\n" +
                "\t\t\t\"text\":\"Java\",\n" +
                "\t\t\t\"attributes\":{\n" +
                "\t\t\t\t\"p1\":\"Custom Attribute1\",\n" +
                "\t\t\t\t\"p2\":\"Custom Attribute2\"\n" +
                "\t\t\t}\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":123,\n" +
                "\t\t\t\"text\":\"Microsoft Office\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":124,\n" +
                "\t\t\t\"text\":\"Games\",\n" +
                "\t\t\t\"checked\":true\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"id\":13,\n" +
                "\t\t\"text\":\"index.html\"\n" +
                "\t},{\n" +
                "\t\t\"id\":14,\n" +
                "\t\t\"text\":\"about.html\"\n" +
                "\t},{\n" +
                "\t\t\"id\":15,\n" +
                "\t\t\"text\":\"welcome.html\"\n" +
                "\t}]\n" +
                "}]\n");
    }

    @RequestMapping("grid")
    public JSONObject grid(){
        return JSON.parseObject("{\"total\":28,\"rows\":[\n" +
                "\t{\"productid\":\"FI-SW-01\",\"productname\":\"Koi\",\"unitcost\":10.00,\"status\":\"P\",\"listprice\":36.50,\"attr1\":\"Large\",\"itemid\":\"EST-1\"},\n" +
                "\t{\"productid\":\"K9-DL-01\",\"productname\":\"Dalmation\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":18.50,\"attr1\":\"Spotted Adult Female\",\"itemid\":\"EST-10\"},\n" +
                "\t{\"productid\":\"RP-SN-01\",\"productname\":\"Rattlesnake\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":38.50,\"attr1\":\"Venomless\",\"itemid\":\"EST-11\"},\n" +
                "\t{\"productid\":\"RP-SN-01\",\"productname\":\"Rattlesnake\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":26.50,\"attr1\":\"Rattleless\",\"itemid\":\"EST-12\"},\n" +
                "\t{\"selected\":true,\"productid\":\"RP-LI-02\",\"productname\":\"Iguana\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":35.50,\"attr1\":\"Green Adult\",\"itemid\":\"EST-13\"},\n" +
                "\t{\"productid\":\"FL-DSH-01\",\"productname\":\"Manx\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":158.50,\"attr1\":\"Tailless\",\"itemid\":\"EST-14\"},\n" +
                "\t{\"productid\":\"FL-DSH-01\",\"productname\":\"Manx\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":83.50,\"attr1\":\"With tail\",\"itemid\":\"EST-15\"},\n" +
                "\t{\"productid\":\"FL-DLH-02\",\"productname\":\"Persian\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":23.50,\"attr1\":\"Adult Female\",\"itemid\":\"EST-16\"},\n" +
                "\t{\"productid\":\"FL-DLH-02\",\"productname\":\"Persian\",\"unitcost\":12.00,\"status\":\"P\",\"listprice\":89.50,\"attr1\":\"Adult Male\",\"itemid\":\"EST-17\"},\n" +
                "\t{\"productid\":\"AV-CB-01\",\"productname\":\"Amazon Parrot\",\"unitcost\":92.00,\"status\":\"P\",\"listprice\":63.50,\"attr1\":\"Adult Male\",\"itemid\":\"EST-18\"}\n" +
                "]}\n");
    }
}
