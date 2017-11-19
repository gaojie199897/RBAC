import org.junit.Test;

/**
 * Created by zhangyu on 2017/10/23.
 */
public class TestRole {


    @Test
    public  void TestcheckUserRoleIsExist(){
        String str ="afdsadfadafafafa";
        String[] split=null;
        String[] a = null;
        for (int i=0;i<str.length();i++){
            split = str.split("");
        }
        for (int i=1;i<split.length;i++){
            int count=0;
            for (int j=1;j<split.length;j++){
                if(split[i].equals(split[j])){
                    count++;
                    continue;
                }
            }
            System.out.println(count);
        }
    }












}
