package commonUtilTests;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liudongdong on 2015/4/11.
 */
public class TestRWLock extends TestCase {
    public static class Student {
        public String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void testMap() {
        final Object sync = new Object();
        final Map<String, Student> keys = new HashMap<>();
        keys.put("l1", new Student("zhangsan"));
        keys.put("l2", new Student("lisi"));
        keys.put("l3", new Student("wangwu"));
        ExecutorService es = Executors.newCachedThreadPool();


        es.execute(()->{
            System.out.println("执行到线程2");
            synchronized (sync)
            {
                try {
                    System.out.println("执行到等待前");
                    sync.wait();
                    System.out.println("线程2等待结束");
                    keys.put("l3",new Student("zhaoliu"));
                    sync.notifyAll();
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        });

        try
        {
            Thread.sleep(5000);
        }
        catch (Exception ex)
        {}

        es.execute(() -> {
            synchronized (sync) {
                keys.forEach((k, v) -> {
                    if (k == "l2") {
                        try {
                            sync.wait();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    System.out.println(keys.get(k));
                    if(k=="l1")
                    {
                        sync.notifyAll();
                    }
                });
            }
        });


    }



}
