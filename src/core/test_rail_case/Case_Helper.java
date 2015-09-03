package core.test_rail_case;

import java.util.ArrayList;

/**
 * Created by Santiago on 02/09/2015.
 */
public class Case_Helper {


    private String run_id="";
    private boolean run_id_set=false;

    private String case_id="";
    private boolean case_id_set=false;

    private String project_id="";
    private boolean project_id_set=false;

    private String suite_id="";
    private boolean suite_id_set=false;

    private String section_id="";
    private boolean section_id_set=false;

    private ArrayList deffects;

    private int type=1;

    public Case_Helper(){
        this.deffects=new ArrayList();
    }

    public String buildCase() throws Exception{



        StringBuilder case_request= new StringBuilder();

        switch(this.type){
            case 1:{

                if(!project_id_set){
                    throw new Exception("Project id is required");
                }

                /*
                This is the case for the get cases method
                 */
                case_request.append("get_cases/"+this.project_id);

                if(this.suite_id_set){
                    case_request.append("&suite_id="+this.suite_id);
                }
                if(this.section_id_set){
                    case_request.append("&section_id="+this.section_id);
                }

                break;
            }

            case 2:{
                    /*
                    This is the case for the update result for a given test case
                     */

                if(!this.run_id_set){
                    throw new Exception("Run id is required");
                }

                case_request.append("add_result_for_case/");

                case_request.append(this.run_id);





                if(!this.case_id_set){
                    throw new Exception("Case id is required");
                }

                case_request.append("/");
                case_request.append(this.case_id);




                break;
            }



        }




        return case_request.toString();
    }


    public Case_Helper add_deffect(String deffect){
        this.deffects.add(deffect);
        return this;
    }

    public boolean has_deffects(){
        return this.deffects.size()>0;
    }

    public ArrayList get_deffects(){
        return this.deffects;
    }



    public Case_Helper type(int type){
        this.type=type;
        return this;
    }

    public Case_Helper run_id(String run_id){
        this.run_id=run_id;
        this.run_id_set=true;
        return this;
    }

    public Case_Helper case_id(String case_id){
        this.case_id=case_id;
        this.case_id_set=true;
        return this;
    }

    public Case_Helper project_id(String project_id){
        this.project_id=project_id;
        this.project_id_set=true;
        return this;
    }

    public Case_Helper suite_id(String suite_id){
        this.suite_id=suite_id;
        this.suite_id_set=true;
        return this;
    }

    public Case_Helper section_id(String section_id){
        this.section_id=section_id;
        this.section_id_set=true;
        return this;
    }







}
