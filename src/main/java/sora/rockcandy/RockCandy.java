package sora.rockcandy;


import sora.rockcandy.proxy.ClientProxy;
import sora.rockcandy.proxy.CommonProxy;
import sora.rockcandy.proxy.IProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RockCandy.MODID)
public class RockCandy {
    public RockCandy instance;
    private static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new CommonProxy());
    public static final String MODID = "rockcandy";
    public static final Logger LOGGER = LogManager.getLogger();

    public RockCandy getInstance() {
        return instance;
    }

    public static IProxy getProxy(){
        return proxy;
    }
}
