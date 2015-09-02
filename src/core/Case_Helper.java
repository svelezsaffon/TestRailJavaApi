package core;

/**
 * Created by Santiago on 02/09/2015.
 */
public class Case_Helper {

    private String project_id="";
    private boolean project_id_set=false;

    private String suite_id="";
    private boolean suite_id_set=false;

    private String section_id="";
    private boolean section_id_set=false;

    public Case_Helper(){

    }

    public String buildCase() throws Exception{


        if(!project_id_set){
                throw new Exception("Project id is required");
        }


        StringBuilder case_request= new StringBuilder("get_cases/"+this.project_id);

        if(this.suite_id_set){
            case_request.append("&suite_id=:"+this.suite_id);
        }

        if(this.section_id_set){
            case_request.append("&section_id=:"+this.section_id);
        }


        return case_request.toString();
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





    /*
            The ID of the user who created the test case
         */
    public static final String CREATED_BY ="created_by";

    /*
    The date/time when the test case was created (as UNIX timestamp)
     */
    public static final String CREATED_ON="created_on";


    /*

     */
    public static final String CONTENT="content";

    /*

     */
    public static final String TIME_ESTIMATE="estimate";

    /*

     */
    public static final String TIME_FORECAST="estimate_forecast";

    /*

     */
    public static final String  CASE_ID="id";

    public static final String  MILESTONE_ID="milestone_id";

    public static final String  PRIORITY_ID="priority_id";

    public static final String  REFS="refs";

    public static final String  SECTION_ID="section_id";

    public static final String  SUITE_ID="suite_id";

    public static final String  TITLE="title";

    public static final String  TYPE_ID="type_id";

    public static final String  UPDATED_BY="updated_by";

    public static final String  UPDATED_OD="updated_on";





}
