import com.cola.omlink.manager.ManagerApplication;
import com.cola.omlink.manager.service.EmailService;
import com.cola.omlink.manager.service.OrderService;
import com.cola.omlink.manager.service.ProjectService;
import com.cola.omlink.manager.service.UserInfoService;
import com.cola.omlink.repository.dto.h5.UserLoginDto;
import com.cola.omlink.repository.dto.h5.UserRegisterDto;
import com.cola.omlink.repository.dto.order.OrderDto;
import com.cola.omlink.repository.entity.product.Project;
import com.cola.omlink.repository.vo.Project.ProjectVo;
import com.cola.omlink.repository.vo.h5.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ManagerApplication.class)
public class functionTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProjectService projectService;


    @Test
    public void testSendCode(){
        String email = "w1015668942@gmail.com";
        emailService.sendValidateCode(email);

    }

    @Test
    public void testRegister(){

        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setUserName("w1015668942@gmail.com");
        userRegisterDto.setPassword("123456");
        userRegisterDto.setNickName("zhangsan");
        userInfoService.register(userRegisterDto);
    }

    @Test
    public void testLogin(){
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUserName("w1015668942@gmail.com");
        userLoginDto.setPassword("123456");
        String token = userInfoService.login(userLoginDto);
        UserVo userVo = userInfoService.getCurrentUserInfo(token);
        System.out.println(userVo.getNickName());

    }

    @Test
    public void testSubmitOrder(){
        OrderDto orderDto = new OrderDto();
        Project project = new Project();
        project.setId(1l);
        project.setHostId(1l);
        project.setProjectName("Meeting");
        project.setProjectAddress("Broadway 111");
        project.setProjectPrice(10.00);
        orderDto.setProject(project);
        System.out.println(orderService.submitOrder(orderDto));
    }

    @Test
    public void testOrder(){
        orderService.cancelOrder(1l);
    }


    @Test
    public void testDelete(){
        ProjectVo projectVo = new ProjectVo();
        projectVo.setProjectId(1l);
        projectVo.setProjectName("shop explore");
        projectVo.setProjectAddress("broadway");
        projectVo.setProjectPrice(100.00);
        projectVo.setContent("recruting!!!!!!!!!!!");
        projectService.deleteProject(projectVo);
    }




}
