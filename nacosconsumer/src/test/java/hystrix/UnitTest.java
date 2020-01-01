package hystrix;

import com.half.nacos.hystrix.CommandHelloWorld;
import com.half.nacos.hystrix.CommandHelloWorld1;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.subjects.ReplaySubject;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;


public class UnitTest {

    @Test
    public void testObservable1() throws Exception {

        Observable<String> fWorld = new CommandHelloWorld("World").observe();
        Observable<String> fBob = new CommandHelloWorld1("Bob").observe();

        // blocking
        System.out.println(fWorld.toBlocking().single());
       // System.out.println(fBob.toBlocking().single());

        // non-blocking
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        fWorld.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                // nothing needed here
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });

        // non-blocking
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        fBob.subscribe(new Action1<String>() {

            @Override
            public void call(String v) {
                System.out.println("onNext: " + v);
            }

        });
    }

    @Test
    public void test1() {
        new CommandHelloWorld("World").observe().subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                // nothing needed here
                System.out.println("执行完1");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });
    }

    @Test
    public void test() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
        subject.onCompleted();


        // both of the following will get the onNext/onCompleted calls from above


        subject.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                // nothing needed here
                System.out.println("执行完1");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });
        subject.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                // nothing needed here
                System.out.println("执行完2");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });
    }

    @Test
    public void testSynchronous() {
        System.out.println(  new CommandHelloWorld("World")
        .execute()) ;
        //System.out.println( new CommandHelloWorld("Bob").execute());
    }

    @Test
    public void testAsynchronous1() throws Exception {
        assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
        assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
    }

    @Test
    public void testAsynchronous2() throws Exception {

        Future<String> fWorld = new CommandHelloWorld("World").queue();
        Future<String> fBob = new CommandHelloWorld("Bob").queue();

        assertEquals("Hello World!", fWorld.get());
        assertEquals("Hello Bob!", fBob.get());
    }

    @Test
    public void testObservable() throws Exception {

        Observable<String> fWorld = new CommandHelloWorld("World").observe();
        Observable<String> fBob = new CommandHelloWorld("Bob").observe();

        // blocking
        assertEquals("Hello World!", fWorld.toBlocking().single());
        assertEquals("Hello Bob!", fBob.toBlocking().single());

        // non-blocking
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        fWorld.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                // nothing needed here
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });

        // non-blocking
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        fBob.subscribe(new Action1<String>() {

            @Override
            public void call(String v) {
                System.out.println("onNext: " + v);
            }

        });

        // non-blocking
        // - using closures in Java 8 would look like this:

        //            fWorld.subscribe((v) -> {
        //                System.out.println("onNext: " + v);
        //            })

        // - or while also including error handling

        //            fWorld.subscribe((v) -> {
        //                System.out.println("onNext: " + v);
        //            }, (exception) -> {
        //                exception.printStackTrace();
        //            })

        // More information about Observable can be found at https://github.com/Netflix/RxJava/wiki/How-To-Use

    }
}