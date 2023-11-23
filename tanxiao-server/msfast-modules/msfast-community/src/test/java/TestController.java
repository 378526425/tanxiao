import cn.hutool.core.util.RandomUtil;
import com.wxmblog.MsfastCommunityApplication;
import com.wxmblog.base.auth.service.MsfConfigService;
import com.wxmblog.community.service.FrUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-10-10 16:22
 **/

@SpringBootTest(classes = MsfastCommunityApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestController {

    @Autowired
    FrUserService frUserService;

    @Autowired
    MsfConfigService msfConfigService;

    @Test
    public void test() {

        for (int i = 0; i < 20; i++) {
            int random = RandomUtil.randomInt(0, 3);
            System.out.println(random + "\n");
        }
       /* Wrapper<FrUserEntity> frUserEntityWrapper = new QueryWrapper<FrUserEntity>().lambda().eq(FrUserEntity::getPhone, "15902393423");
        FrUserEntity frUserEntity = frUserService.getOne(frUserEntityWrapper);
        System.out.println(frUserEntity);

        Wrapper<MsfConfigEntity> sysConfigEntityWrapper = new QueryWrapper<MsfConfigEntity>().lambda().eq(MsfConfigEntity::getCode, "");
        MsfConfigEntity msfConfigEntity = msfConfigService.getOne(sysConfigEntityWrapper);
        System.out.println(msfConfigEntity);*/
    }
}
