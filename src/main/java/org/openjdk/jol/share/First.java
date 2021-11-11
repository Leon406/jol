package org.openjdk.jol.share;

import me.leon.java.User;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.samples.JOLSample_01_Basic;
import org.openjdk.jol.samples.JOLSample_15_IdentityHashCode;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class First {
    public static void main(String[] args) {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(User.class).toPrintable());
        out.println(ClassLayout.parseClass(me.leon.User.class).toPrintable());
       final User user = new User("leon1666",666);
       final me.leon.User ktUser = new me.leon.User("leon1666",666);
        ClassLayout userLayout = ClassLayout.parseInstance(user);
        out.println(userLayout.toPrintable());
        out.println(GraphLayout.parseInstance(user).totalSize());
        out.println(GraphLayout.parseInstance(ktUser).toPrintable());
        ClassLayout ktUserLayout = ClassLayout.parseInstance(ktUser);
        out.println(ktUserLayout.toPrintable());
        out.printf("user hash: %s", Integer.toHexString(user.hashCode()));
        out.printf("kt user hash: %s", Integer.toHexString(ktUser.hashCode()));

        out.println(userLayout.toPrintable());
        out.println(ktUserLayout.toPrintable());


        final User a = new User("leon",666);

        ClassLayout layout = ClassLayout.parseInstance(a);

        out.println("**** Fresh object");
        out.println(layout.toPrintable());

        out.println("hashCode: " + Integer.toHexString(a.hashCode()));
        out.println();

        out.println("**** After identityHashCode()");
        out.println(layout.toPrintable());


    }

}
