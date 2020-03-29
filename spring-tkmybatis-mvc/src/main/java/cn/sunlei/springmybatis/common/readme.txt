
实体类创建的经验

对于注入属性时 如果很多的层级关系时 可以使用内部类的方式区分开

例如
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "quartz.job")
public class QuartzJobModelProperties {
    private String triggerGroupName = "d2i_trigger";
    private String jobGroupName = "d2i_job";

    private D2iQuartzJobModel alerts;
    private D2iQuartzJobModel availability;
    private D2iQuartzJobModel contracts;
    private D2iQuartzJobModel devices;
    private D2iQuartzJobModel downtime;
    private D2iQuartzJobModel parameterValue;
    private D2iQuartzJobModel serviceCall;

    @Setter
    @Getter
    public static class D2iQuartzJobModel {
        String triggerKey;
        String jobName;
        String corn;
    }
}