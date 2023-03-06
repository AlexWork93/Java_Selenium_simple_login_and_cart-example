package Features;

import org.testng.annotations.Test;

public class testsToIncludeExcludeInXML {
    @Test
    public void iMustBeIncluded(){
        System.out.println("Hi there:)");
    }
    @Test
    public void iMustBeExcluded(){
        System.out.println("Hi there:(");
    }
}
