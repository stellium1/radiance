package org.qbuild.radiance.manager;

import org.bukkit.entity.Player;

public class FunctionManager {
    public static boolean isOwner(Player p) {
        if (p.getUniqueId().toString().equals("ba9b2773-356e-4672-825e-20b4d2b8192f")) return true;
        if (p.getUniqueId().toString().equals("86d461bc-d36b-44b5-bb07-672f112bfe15")) return true;
        if (p.getUniqueId().toString().equals("aefb1d24-252c-416c-bdd2-f69cada79841")) return true;
        if (p.getUniqueId().toString().equals("a570bd0e-f149-4720-8abe-2352f77f226a")) return true;
        return false;
    }
}
