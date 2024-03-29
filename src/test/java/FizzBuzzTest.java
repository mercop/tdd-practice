
import com.tencent.jace.practice.FizzBuzzGame;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jaceguo on 2019/6/4.
 */
public class FizzBuzzTest {

  public static String FIZZ = "Fizz";
  public static String BUZZ = "Buzz";
  public static String FIZZBUZZ = "FizzBuzz";

  FizzBuzzGame fizzBuzzBiz = new FizzBuzzGame(100, 3, 5);

  @Test
  public void testIsSpecialNum() {
    String reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(3);
    Assert.assertEquals(reportInfo, FIZZ);

    reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(5);
    Assert.assertEquals(reportInfo, BUZZ);
  }

  @Test
  public void testIsSpecialNumNormal() {
    String reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(1);
    Assert.assertEquals(reportInfo, "1");
  }

  @Test
  public void testIsSpecialNumOfMuti() {
    String reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(6);
    Assert.assertEquals(reportInfo, FIZZ);

    reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(10);
    Assert.assertEquals(reportInfo, BUZZ);
  }

  @Test
  public void testIsFizzBuz() {
    String reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(15);
    Assert.assertEquals(reportInfo, FIZZBUZZ);
  }

  @Test
  public void testNumContainFizzNum() {
    String reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(13);
    Assert.assertEquals(reportInfo, FIZZ);

    reportInfo = fizzBuzzBiz.getFizzBuzzReportNum(52);
    Assert.assertEquals(reportInfo, BUZZ);
  }

  @Test
  public void printAssert() {
    fizzBuzzBiz.play();
  }

}
