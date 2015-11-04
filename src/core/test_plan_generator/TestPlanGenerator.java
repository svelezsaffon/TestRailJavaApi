package core.test_plan_generator;

import core.API_CORE;
import core.test_rail_case.StaticCodes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by Santiago on 04/09/2015.
 */

public class TestPlanGenerator {



    private API_CORE test_rail;

    private JSONObject test_run;

    private JSONArray tests_in_run;

    public  TestPlanGenerator(String username,String password,String url) throws Exception{
        this.test_rail=new API_CORE();
        this.test_rail.set_username(username);
        this.test_rail.set_url(url);
        this.test_rail.set_password(password);
        this.test_rail.connect();
    }


    public void setTestRun(int runId) throws Exception{
        this.test_run=  this.test_rail.get_test_run(runId);
        System.out.println(this.test_run);
    }



    public void setTestRunTestCaseCollection(int runid) throws Exception {
        this.tests_in_run=this.test_rail.get_tests_cases_in_run(runid);
    }

    public void exportRunToFile(String file_directory,List<String> fields) throws Exception {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

        StringBuilder cal=new StringBuilder(file_directory);

        cal.append("[");
        cal.append(today.get(Calendar.DAY_OF_MONTH));
        cal.append("-");
        cal.append(today.get(Calendar.MONTH));
        cal.append("-");
        cal.append(today.get(Calendar.YEAR));
        cal.append("].txt");


        File file=new File(cal.toString());
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw=new FileWriter(file.getAbsoluteFile());

        BufferedWriter writer=new BufferedWriter(fw);

        if(this.tests_in_run.size()!=0){
            int size=this.tests_in_run.size();

            for(String field:fields){
                writer.write("|"+field.toUpperCase()+"\t");
            }


            writer.newLine();

            StringBuilder out=new StringBuilder();



            for(int i=0;i<size;i++){
                JSONObject caze=(JSONObject)this.tests_in_run.get(i);


                for(String field:fields){
                    out.append(caze.get(field).toString());
                    out.append("\t\t");
                }


                out.append("\t");

                writer.write(out.toString());
                writer.newLine();

                out.setLength(0);
            }

        }else{
            throw new Exception("The TesRun is empty, or is invalid");
        }

        writer.close();



    }


    public void exportRunToFile(String file_directory) throws Exception {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

        StringBuilder cal=new StringBuilder(file_directory);

        cal.append("[");
        cal.append(today.get(Calendar.DAY_OF_MONTH));
        cal.append("-");
        cal.append(today.get(Calendar.MONTH));
        cal.append("-");
        cal.append(today.get(Calendar.YEAR));
        cal.append("].txt");


        File file=new File(cal.toString());
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw=new FileWriter(file.getAbsoluteFile());

        BufferedWriter writer=new BufferedWriter(fw);

        if(this.tests_in_run.size()!=0){
            int size=this.tests_in_run.size();

            List <String> fields=new ArrayList<String>();
            fields.add(StaticCodes.RUN_ID);
            fields.add(StaticCodes.CASE_ID);
            fields.add(StaticCodes.TITLE);


            for(String field:fields){
                writer.write("|"+field.toUpperCase()+"\t");
            }


            writer.newLine();

            StringBuilder out=new StringBuilder();



            for(int i=0;i<size;i++){
                JSONObject caze=(JSONObject)this.tests_in_run.get(i);

                for(String field:fields){
                    out.append(caze.get(field).toString());
                    out.append("\t\t");
                }

                long status=Long.parseLong( caze.get(StaticCodes.STATUS_ID).toString());

                switch((int)status){
                    case StaticCodes.FAILED_STATUS_CODE:
                        out.append("FAILED");
                        break;
                    case StaticCodes.PASSED_STATUS_CODE:
                        out.append("PASSED");
                        break;
                    case StaticCodes.BLOCKED_STATUS_CODE:
                        out.append("BLOCKED");
                        break;
                    case StaticCodes.RETEST_STATUS_CODE:
                        out.append("RETEST");
                        break;
                    default:
                        out.append("UNTESTED");
                        break;
                }

                out.append("\t");

                writer.write(out.toString());
                writer.newLine();

                out.setLength(0);
            }
            //writer.write(out.toString());
        }else{
            throw new Exception("The TesRun is empty, or is invalid");
        }

        writer.close();



    }




}
