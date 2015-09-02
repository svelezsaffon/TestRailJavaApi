package run;

import core.API_CORE;
import core.Case_Helper;

/**
 * Created by Santiago on 02/09/2015.
 */
public class MainClass {


    public static void main(String args[]){
        API_CORE api=new API_CORE();

        try {
            api.init();

            Case_Helper help=new Case_Helper().project_id("1");
            System.out.println(api.get_cases(help));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
