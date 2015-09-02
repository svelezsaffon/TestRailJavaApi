package run;

import core.API_CORE;
import core.test_rail_case.CaseCodes;
import core.test_rail_case.Case_Helper;

import java.util.Map;
import java.util.HashMap;
/**
 * Created by Santiago on 02/09/2015.
 */
public class MainClass {


    public static void main(String args[]){
        API_CORE api=new API_CORE();

        try {

            api.init();

            Case_Helper help=new Case_Helper().run_id("3").type(2).case_id("1");

            Map data= new HashMap();

            data.put(CaseCodes.STATUS_ID,new Integer(CaseCodes.PASSED_STATUS_CODE));

            data.put(CaseCodes.COMMENT,"This is weird!!!");

            System.out.println(api.add_result_for_case(data, help));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
