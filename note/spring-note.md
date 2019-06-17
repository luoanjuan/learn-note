## Spring中扩展

#### BeanFactoryPostProcessor.postProcessBeanFactory
允许定制修改spring上下文中bean的定义，调整上下文中bean工厂中的bean的属性值。beanFactoryPostProcessor可以与bean定义交互和修改bean定义，但是不能修改bean实例。这样做或导致过早的bean实例化，违反容器规则容易导致意想不到后果。
在所有bean定义都被加载，但是没有bean被初始化时调用。
###### BeanDefinitionRegistryPostProcessor.BeanDefinitionRegistryPostProcessor
BeanDefinitionRegistryPostProcessor是BeanFactoryPostProcessor的子接口，主要用于向bean工厂注册bean，调用在postProcessBeanFactory方法之前。

#### BeanPostProcessor
//应用在所有bean实例化方法之前(例如InitializingBean的afterPropertiesSet和init-method)，这个bean的属性已经被赋值，返回值可能是原始bean的包装类。
default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
//应用所有bean实例化方法之后(例如InitializingBean的afterPropertiesSet和init-method)，这个bean的属性已经被赋值。这个方法可以被用于FactoryBean实例或者它创建的bean
default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
子接口

###### InstantiationAwareBeanPostProcessor
//在实例化bean之前调用，可能返回的是一个代理而不是目标bean。如果这个方法返回一个非空对象，后面的bean创建过程会被忽略，只会调用postProcessAfterInstantiation这个方法。
default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException
//实例化bean之后调用，在显示属性赋值和属性注入之前。返回true表示应该设置bean的属性，返回false表示应该跳过属性填充，如果返回false那么InstantiationAwareBeanPostProcessor
将不会在这个bean对象上调用
default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException
###### DestructionAwareBeanPostProcessor
调用bean的销毁方法，比如DisposableBean的destroy方法

###### SmartInstantiationAwareBeanPostProcessor
智能bean后处理器

//预测bean的类型
default Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException
//选择合适的bean构造器
default Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName)
//解决循环引用的问题
default Object getEarlyBeanReference(Object bean, String beanName) throws BeansException

###### MergedBeanDefinitionPostProcessor
合并bean定义使用，大概就是将一个bean的定义注入另外一个bean中。CommonAnnotationBeanPostProcessor子类实现对@Resource的支持，子类
AutowiredAnnotationBeanPostProcessor实现对对@Value和@Autowire支持

