package tp34;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class SemaphoreJUnitTest {

  @SuppressWarnings("rawtypes")
private Class mySemImpl;

  @Before
  public void obtainSemaphoreImplementation() 
    throws ClassNotFoundException {
    mySemImpl = SemaphoreImpl.class;
    
  }

  @SuppressWarnings("deprecation")
private SemaphoreInterface createSemaphore() 
    throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return (SemaphoreInterface)mySemImpl.newInstance();
  }

  protected void upThenDown(SemaphoreInterface sem, int count) {
    for(int i = 1; i <= count; i++) {
      for(int k = 0; k < i; k++)
        sem.up();
      for(int k = 0; k < i; k++)
        sem.down();
    } // EndFor i
  } // EndMethod upThenDown

  @Test(timeout = 40000)
  public void testUpThenDownShouldNotBlockSingleThread() throws Exception {
    SemaphoreInterface sem = createSemaphore();
    upThenDown(sem,10000);
  } // EndMethod testUpThenDownShouldNotBlockSingleThread

  @Test(timeout = 40000)
  public void testUpThenDownShouldNotBlockMultipleThreads() throws Exception {
    SemaphoreInterface sem        = createSemaphore();
    Collection<Thread> allThreads = new Vector<Thread>();
    for(int i = 1; i <= 40 ; i++) {
      Thread t = new UpThenDownThread(sem,this);
      t.start();
      allThreads.add(t);
    }
    for(Thread t: allThreads)
      t.join();
    
  } // EndMethod testUpThenDownShouldNotBlockMultipleThreads

  @Test
  public void testThatDownDoesBlock() throws Exception {
    SemaphoreInterface sem = createSemaphore();
    Thread t = new DowningThread(sem,this);
    t.start();
    Thread.sleep(1000); // 1s = very long in terms of CPU time
    assertTrue(t.isAlive());
    sem.up();
    t.join(1000);
    assertFalse(t.isAlive()); // t should now have finished
  } // EndMethod testThatDownDoesBlock

  private int countAliveThreads(Collection<Thread> allThreads) {
    int res = 0;
    for(Thread t: allThreads)
      if (t.isAlive()) res++;
    return res;
  } // EndMethod countAliveThreads

  @Test(timeout = 40000)
  public void testThatUpUnblocksBlockedThreads() throws Exception {
    SemaphoreInterface sem = createSemaphore();
    Collection<Thread> allThreads = new Vector<Thread>();
    for(int i = 0; i < 10 ; i++) {
      Thread t = new DowningThread(sem,this);
      t.start();
      //System.out.println("ha\n");
      allThreads.add(t);
    }
    Thread.sleep(1000); // leaving some time for threads to start and block
    assertEquals(10,countAliveThreads(allThreads));
    for(int i = 9; i >= 0 ; i--) {
      sem.up(); // should unblock one thread
      do {
        Thread.sleep(10); // Busy waiting
      } while(countAliveThreads(allThreads)!=i);
    } // EndFor i
    assertEquals(0,countAliveThreads(allThreads));
  } // EndMethod testThatUpUnblocksBlockedThreads

  @Test(timeout = 40000)
  public void testThatUpUnblocksBlockedThreadsWithUpperThreads() throws Exception {
    SemaphoreInterface sem = createSemaphore();
    Collection<Thread> allThreads = new Vector<Thread>();
    for(int i = 0; i < 100 ; i++) {
      Thread t = new DowningThread(sem,this);
      t.start();
      allThreads.add(t);
    }
    Thread.sleep(1000); // leaving some time for threads to start and block
    for(int i = 0; i < 100 ; i++) {
      Thread t = new UppingThread(sem,this);
      t.start();
      allThreads.add(t);
    }
    // all threads should finish, including blocked ones
    for(Thread t: allThreads)
      t.join();
  } // EndMethod testThatUpUnblocksBlockedThreadsWithUpperThreads

  
  @Test(timeout = 40000)
  public void testThatReleaseAllWorksWithNoThreadWaiting() throws Exception {
    SemaphoreInterface sem = createSemaphore();
    // testing that release all and up do now interfere
    for(int i=0; i < 100; i++) {
      assertEquals(0,sem.releaseAll());
      sem.up();
    } // EndFor
  } // EndMethod testThatReleaseAllWorksWithNoThreadWaiting

  @Test(timeout = 40000)
  public void testThatReleaseAllWorksWithThreadsWaiting() throws Exception {
    SemaphoreInterface sem = createSemaphore();
    for(int nbWaitingThread=0; nbWaitingThread < 100; nbWaitingThread++) {

      // launching the downing threads
      Collection<Thread> allThreads = new Vector<Thread>();
      for(int i = 0; i < nbWaitingThread ; i++) {
        Thread t = new DowningThread(sem,this);
        t.start();
        allThreads.add(t);
      } // EndFor i
      
      // releasing blocked threads. We use a while loop as we don't
      // know how long the downing threads will take to initialise.
      int totalReleased = 0;
      while(totalReleased<nbWaitingThread) {
        totalReleased += sem.releaseAll();
        Thread.yield();
      } // EndWhile

      // all threads on the semaphore should now be released
      assertEquals(0,sem.releaseAll());

      // all threads should have finish
      for(Thread t: allThreads)
        t.join();
    } // EndFor
  } // EndMethod testThatReleaseAllWorksWithThreadsWaiting

  @Test(timeout = 40000)
  public void testStressWorkloadWithAllConcurrent() throws Exception {
    SemaphoreInterface sem = createSemaphore();
    Collection<Thread> allThreads = new Vector<Thread>();
    // we first create a number of threads blocked on a down operation.
    for(int i = 0; i < 200 ; i++) {
      Thread t = new DowningThread(sem,this);
      t.start();
      allThreads.add(t);
    } // EndFor i
    // we then create 40 threads doing up and down
    for(int i = 1; i <= 40 ; i++) {
      Thread t = new UpThenDownThread(createSemaphore(),this);
      t.start();
      allThreads.add(t);
    }

    // releaseAll should unblock 200 threads in total
    int totalReleased = 0;
    while(totalReleased!=200) {
      totalReleased += sem.releaseAll();
      Thread.yield();
    } // EndWhile
    
    // all threads on the semaphore should now be released
    assertEquals(0,sem.releaseAll());

    // all threads should have finished
    for(Thread t: allThreads)
      t.join();

  } // EndMethod testStressWorkloadWithAllConcurrent

} // EndClass SemaphoreJUnitTest

class TestingThread extends Thread {
  protected SemaphoreInterface mySemaphore;
  protected SemaphoreJUnitTest myTestCase;

  public TestingThread(SemaphoreInterface aSemaphore, SemaphoreJUnitTest aTestCase) {
    mySemaphore = aSemaphore;
    myTestCase  = aTestCase;
  }
} // EndClass TestingThread

class UpThenDownThread extends TestingThread {

  public UpThenDownThread(SemaphoreInterface aSemaphore, SemaphoreJUnitTest aTestCase) {
    super(aSemaphore,aTestCase);
  }

  public void run() {
    myTestCase.upThenDown(mySemaphore,2000);
  } // EndMethod run

} // EndClass UpThenDownThread

class DowningThread extends TestingThread {

  public DowningThread(SemaphoreInterface aSemaphore, SemaphoreJUnitTest aTestCase) {
    super(aSemaphore,aTestCase);
  }

  public void run() {
    mySemaphore.down();
  } // EndMethod run
} // EndClass DowningThread

class UppingThread extends TestingThread {

  public UppingThread(SemaphoreInterface aSemaphore, SemaphoreJUnitTest aTestCase) {
    super(aSemaphore,aTestCase);
  }

  public void run() {
    mySemaphore.up();
  } // EndMethod run
} // EndClass UppingThread
