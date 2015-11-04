package core.test_rail_case;

import java.util.ArrayList;

/**
 * This class represents a TestRail test case, tt has additional helper methods.
 */
public class TestCase {


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

    public TestCase(){
        this.deffects=new ArrayList();
    }


    /**
     * @return
     * @throws Exception
     */
    public String buildCase() throws Exception{

        StringBuilder case_request= new StringBuilder();

        switch(this.type){
            case 1:{

                if(!project_id_set){
                    throw new Exception("Project id is required");
                }

                /**
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
                    /**
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


    public TestCase add_deffect(String deffect){
        this.deffects.add(deffect);
        return this;
    }


    /**
     * checks if defects has been added to the current case
     * @return true if the case has at least one defect
     */
    public boolean has_defects(){
        return this.deffects.size()>0;
    }

    /**
     * Returns a list that contains the defects, this list may or may be not empty
     * @return Array list containing the strings
     */
    public ArrayList get_deffects(){
        return this.deffects;
    }


    /**
     * @param type Type of operation, if the operation is either pull or push. Method return THIS to be able to use it in the constructor <br>
     * <h1>Example</h1>
     * Case_Helper help= new Case_Helper().run_id("4").type(CaseCodes.ADD_RESULTS_TYPE).case_id("3");
     * @see core.test_rail_case.StaticCodes#ADD_RESULTS_TYPE
     * @see core.test_rail_case.StaticCodes#GET_CASE_TYPE
     * @return this
     */
    public TestCase type(int type){
        this.type=type;
        return this;
    }


    /**
     * Sets the Test Run id for which we will be retrieving or modifying information. Method return THIS to be able to use it in the constructor <br>
     * <h1>Example</h1>
     * Case_Helper help= new Case_Helper().run_id("4").type(CaseCodes.ADD_RESULTS_TYPE).case_id("3");
     * @param run_id
     * @see core.test_rail_case.StaticCodes#RUN_ID
     * @return
     */
    public TestCase run_id(String run_id){
        this.run_id=run_id;
        this.run_id_set=true;
        return this;
    }


    /**
     * Sets the Test Run id for which we will be retrieving or modifying information. Method return THIS to be able to use it in the constructor <br>
     * <h1>Example</h1>
     * Case_Helper help= new Case_Helper().run_id("4").type(CaseCodes.ADD_RESULTS_TYPE).case_id("3");
     * @param case_id
     * @return this
     * @see core.test_rail_case.StaticCodes#CASE_ID
     */
    public TestCase case_id(String case_id){
        this.case_id=case_id;
        this.case_id_set=true;
        return this;
    }

    /**
     * Sets the project ID of the current Test Case. Method return THIS to be able to use it in the constructor <br>
     * <h1>Example</h1>
     * Case_Helper help= new Case_Helper().run_id("4").type(CaseCodes.ADD_RESULTS_TYPE).case_id("3");
     *
     * @param project_id
     * @return
     */
    public TestCase project_id(String project_id){
        this.project_id=project_id;
        this.project_id_set=true;
        return this;
    }

    /**
     *Sets the set identifier to the current case. Method return THIS to be able to use it in the constructor <br>
     * <h1>Example</h1>
     * Case_Helper help= new Case_Helper().run_id("4").type(CaseCodes.ADD_RESULTS_TYPE).case_id("3");
     * @param suite_id
     * @return
     */
    public TestCase suite_id(String suite_id){
        this.suite_id=suite_id;
        this.suite_id_set=true;
        return this;
    }

    /**
     *
     * @param section_id
     * @return
     */
    public TestCase section_id(String section_id){
        this.section_id=section_id;
        this.section_id_set=true;
        return this;
    }




}
