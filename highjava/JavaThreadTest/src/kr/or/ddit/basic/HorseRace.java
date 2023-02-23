package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Horse implements Runnable, Comparable<Horse> {
  private String name;
  private int rank;

  public Horse(String name) {
    this.name = name;
    this.rank = 0;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }

  public String getName() {
    return name;
  }

  @Override
  public int compareTo(Horse o) {
    return this.rank - o.rank;
  }

  @Override
  public void run() {
    Random random = new Random();
    for (int i = 1; i <= 50; i++) {
      int distance = random.nextInt(10) + 1;
      setRank(getRank() + distance);
      System.out.println(getName() + " " + getRank());
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class HorseRace {
  public static void main(String[] args) {
    ArrayList<Horse> horses = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      horses.add(new Horse("Horse " + i));
    }

    ArrayList<Thread> threads = new ArrayList<>();
    for (Horse horse : horses) {
      threads.add(new Thread(horse));
    }

    for (Thread thread : threads) {
      thread.start();
    }

    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    Collections.sort(horses);
    System.out.println("Final ranking:");
    for (Horse horse : horses) {
      System.out.println(horse.getName() + " " + horse.getRank());
    }
  }
}
