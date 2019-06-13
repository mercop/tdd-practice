package com.tencent.jace.practice;

/**
 * Created by jaceguo on 2019/6/4.
 */
public class FizzBuzzGame {

  private int studentNums;

  private int fizzNum;
  private int buzzNum;

  public FizzBuzzGame(int studentNums, int fizzNum, int buzzNum) {
    this.studentNums = studentNums;
    this.fizzNum = fizzNum;
    this.buzzNum = buzzNum;
  }

  /**
   * 获取真实报数字符.
   * @param num
   * @return
   */
  public String getFizzBuzzReportNum(int num) {

    if (checkIsFizzBuzzNum(num)) {
      return Constant.FIZZBUZZ_REPORT;
    }

    if (checkIsFizz(num)) {
      return Constant.FIZZ_REPORT;
    }

    if (checkIsBuzz(num)) {
      return Constant.BUZZ_REPORT;
    }

    return String.valueOf(num);
  }

  private boolean checkIsBuzz(int count) {
    if (count % buzzNum == 0) {
      return true;
    }
    if (containSpecialNum(count, buzzNum)) {
      return true;
    }
    return false;
  }

  private boolean containSpecialNum(int count, int specialNum) {
    int curVal = count;
    while(curVal > 1) {
      if (specialNum == curVal % 10) {
        return true;
      }
      curVal /= 10;
    }
    return false;
  }

  private boolean checkIsFizz(int count) {
    if (count % fizzNum == 0) {
      return true;
    }

    if (containSpecialNum(count, fizzNum)) {
      return true;
    }
    return false;
  }

  private boolean checkIsFizzBuzzNum(int count) {
    if (checkIsFizz(count) && checkIsBuzz(count)) {
      return true;
    }
    return false;
  }

  /**
   * 打印报数结果.
   */
  public void play() {
    StringBuilder reportBuilder = new StringBuilder();
    for (int i = 0; i < studentNums; i++) {
      reportBuilder.append(getFizzBuzzReportNum(i));
      reportBuilder.append("\n");
    }
    System.out.println(reportBuilder.toString());
  }

  public static void main(String[] args) {
    FizzBuzzGame fizzBuzzGame = new FizzBuzzGame(100, 3, 5);
    fizzBuzzGame.play();
  }
}
