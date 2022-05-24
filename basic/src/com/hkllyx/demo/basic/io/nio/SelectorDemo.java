package com.hkllyx.demo.basic.io.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * 1）一种由SelectableChannel对象组成的多路复用器。<p>
 * 2）调用该类的静态方法open()使用系统默认选择器提供程序来创建选择器。 选择器在调用其close()方法之前保持打开状态。<p>
 * 3）可选择通道与选择器的注册由SelectionKey对象表示。选择器维护三组SelectionKey集:<br>
 * <ul>
 *     <li>key集：包含注册此选择器的通道的键。通过keys()方法获取。</li>
 *     <li>
 *         selected-key集：键对应的通道在之前一个selected后至少有一个可用操作。
 *         使用selectedKeys()方法获取。始终是key集的子集。
 *     </li>
 *     <li>cancelled-key集：是已取消但其通道尚未注销的键集。不能直接访问。始终是key集的子集。</li>
 * </ul>
 * 在新创建的选择器中，所有三个集合都是空的。
 * <p>
 * 4）通过通道的register()方法将通道会将键添加到选择器的键集中。在选择操作期间，已取消的键将从键集中删除。键集本身不能直接修改。
 * <p>
 * 5）通过关闭key的通道或调用cancel()可以将key添加canceled-key集。
 * 取消将导致其通道在下一次选择操作期间注销，键将从选择器的所有键集中删除。
 * <p>
 * 6）通过select()方法将键添加到selected-key集。selected-key集可以且仅可以通过集合的remove方法或Iterator的remove方法移除。
 *
 * @author HKLLY
 * @date 2019-07-19
 */
public class SelectorDemo {
    
    public static void main(String[] args) {
        try {
            //开启选择器
            Selector selector = Selector.open();
            //测试是否开启
            boolean isOpen = selector.isOpen();
            
            //选择一组键，其相应的通道已准备好进行IO操作。该方法执行阻塞选择操作。只有在选择了至少一个通道，
            //wakeup()此选择器的方法或当前线程被中断（以先到者为准）后，它才会返回。
            int i = selector.select();
            //...只有在选择了至少一个通道，wakeup调用此选择器的方法，中断当前线程或给定的超时时间到期时（以先到者为准），它才会返回。
            i = selector.select(1000L);
            //选择一组键，其相应的通道已准备好进行IO操作。该方法执行非阻塞选择操作。
            //  如果自上次选择操作后没有可选择的通道，则此方法立即返回零。
            //调用此方法可以清除以前调用wakeup()的效果
            i = selector.selectNow();
            
            //返回此选择器的键集。
            Set<SelectionKey> keySet = selector.keys();
            //返回此选择器的选择键集。
            keySet = selector.selectedKeys();
            
            //创建此通道的提供程序。
            SelectorProvider provider = selector.provider();
            
            //导致尚未返回的第一个选择操作立即返回。
            //如果在调用select()或select(long)方法时当前阻止了另一个线程， 那么该调用将立即返回。
            // 如果当前没有选择操作正在进行，除非selectNow()在此期间调用该方法，否则将立即返回其中一个方法的下一次调用。
            // 在任何情况下，该调用返回的值可能都不为零。除非在此期间再次调用此方法，否则后续调用select()或select(long)
            // 将像往常一样进行阻止。
            //在两个连续的选择操作之间多次调用此方法与仅调用一次具有相同的效果。
            selector = selector.wakeup();
            
            //关闭此选择器。
            //如果一个线程当前在这个选择器的一个选择方法中被阻塞，那么它就像通过调用选择器的wakeup方法一样被中断。
            //仍然与此选择器关联的任何未取消的密钥无效，其通道被取消注册，并且释放与此选择器关联的任何其他资源。
            //如果此选择器已关闭，则调用此方法无效。
            //关闭选择器之后，除了通过调用此方法或wakeup方法之外，任何进一步尝试使用它都将导致 ClosedSelectorException抛出a。
            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
