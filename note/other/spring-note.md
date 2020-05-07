## 容器中注册组件
1. 包扫描+组件标注注解（@ComponentScan标注需要扫描的包）
2. @Bean 导入第三方包里面的组件
3. @Import 快速给容器中导入一个组件
    1). @Import(要导入到容器中的组件)：容器会自动注册这个组件，id默认是全类名  
    2). ImportSelector:返回需要导入的组件的全类名数组  
    3). ImportBeanDefinitionRegistrar 手动注册bean到容器中  
4. 使用Spring提供的FactoryBean（工场bean）。默认获取到的是工厂bean调用getObject创建的对象，获取工厂bean本身，需要在前面添加&


## Spring bean中生命周期

1. 指定初始化和销毁方法，通过init-method和destroy-method方法
2. 通过让Bean实现InitializingBean（定义初始化逻辑），DisposableBean(定义销毁逻辑）
3. 可以使用JSR250：@PostConstruct 在bean创建完成并且属性赋值完成，来执行初始化方法。@PreDestroy 在容器销毁bean之前通知我们进行清理工作
4. BeanPostProcessor(interface): bean的后置处理器，在bean初始化前后进行一些处理工作：
   postProcessBeforeInitialization:在初始化之前工作；postProcessAfterInitialization:在初始化之后工作
   

## Spring中扩展
#### BeanFactoryPostProcessor.postProcessBeanFactory
允许定制修改spring上下文中bean的定义，调整上下文中bean工厂中的bean的属性值。beanFactoryPostProcessor可以与bean定义交互和修改bean定义，但是不能修改bean实例。这样做或导致过早的bean实例化，违反容器规则容易导致意想不到后果。
在所有bean定义都被加载，但是没有bean被初始化时调用。
###### BeanDefinitionRegistryPostProcessor.BeanDefinitionRegistryPostProcessor
BeanDefinitionRegistryPostProcessor是BeanFactoryPostProcessor的子接口，主要用于向bean工厂注册bean，调用在postProcessBeanFactory方法之前。

#### BeanPostProcessor
//应用在所有bean初始化方法之前(例如InitializingBean的afterPropertiesSet和init-method)，这个bean的属性已经被赋值，返回值可能是原始bean的包装类。  
default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException  
//应用所有bean初始化方法之后(例如InitializingBean的afterPropertiesSet和init-method)，这个bean的属性已经被赋值。这个方法可以被用于FactoryBean实例或者它创建的bean  
default Object BeanPostProcessor#postProcessAfterInitialization(Object bean, String beanName) throws BeansException
子接口

###### InstantiationAwareBeanPostProcessor
//在实例化bean之前调用，可能返回的是一个代理而不是目标bean。如果这个方法返回一个非空对象，后面的bean创建过程会被忽略，只会调用postProcessAfterInitialization这个方法。  
default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException  
//实例化bean之后调用，在显示属性赋值和属性注入之前。返回true表示应该设置bean的属性，返回false表示应该跳过属性填充，如果返回false那么后续InstantiationAwareBeanPostProcessor
实例将不会在这个bean对象上调用  
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


##Spring中启动过程

    Resource resource = classPathXmlApplicationContext.getResource("resources/spring.xml");
    classPathXmlApplicationContext.refresh();
    
    DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) classPathXmlApplicationContext.getBeanFactory();
    XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    xmlBeanDefinitionReader.loadBeanDefinitions(resource);


xmlBeanDefinitionReader.loadBeanDefinitions(resource) ——><br/>
——>XmlBeanDefinitionReader.doLoadBeanDefinitions(InputSource inputSource, Resource resource)<br/>
——> XmlBeanDefinitionReader.registerBeanDefinitions(Document doc, Resource resource)<br/>
——> BeanDefinitionDocumentReader.registerBeanDefinitions(Document doc, XmlReaderContext readerContext) (父类中方法)<br/>
——> BeanDefinitionDocumentReader.doRegisterBeanDefinitions(Element root) (父类中方法)<br/>
——> BeanDefinitionDocumentReader.parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) (父类中方法)<br/>

#####默认命名空间的bean
XmlBeanDefinitionReader.parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate)(父类DefaultBeanDefinitionDocumentReader的方法)<br/>
XmlBeanDefinitionReader.processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate)(父类DefaultBeanDefinitionDocumentReader的方法)<br/>
BeanDefinitionParserDelegate.parseBeanDefinitionElement(Element ele, @Nullable BeanDefinition containingBean)<br/>
BeanDefinitionParserDelegate.parseBeanDefinitionElement(Element ele, @Nullable BeanDefinition containingBean)<br/>
BeanDefinitionParserDelegate.createBeanDefinition(@Nullable String className, @Nullable String parentName)<br/>
BeanDefinitionReaderUtils.createBeanDefinition(parentName, className, this.readerContext.getBeanClassLoader());

#####定制标签解析
——> BeanDefinitionParserDelegate.parseCustomElement(Element ele)<br/>
——> BeanDefinitionParserDelegate.parseCustomElement(Element ele, @Nullable BeanDefinition containingBd)找到对应的NamespaceHandler，handler.parse进行解析<br/>
——> NamespaceHandlerSupport.parse(Element element, ParserContext parserContext)(实际是NamespaceHandlerSupport子类的调用)，找到对应的BeanDefinitionParser，调用parse得到BeanDefinition。


## Spring AOP
<aop:aspectj-autoproxy /> 标签在AopNamespaceHandler(jar包中META-INF中的spring.handlers)中定义了对应的解析类AspectJAutoProxyBeanDefinitionParser。
这个解析类向AOP中注入了AnnotationAwareAspectJAutoProxyCreator，AnnotationAwareAspectJAutoProxyCreator实现了Spring中的扩展接口，真正实现了AOP功能。

AnnotationAwareAspectJAutoProxyCreator.postProcessAfterInitialization():父类AbstractAutoProxyCreator中 <br/>
-> AbstractAutoProxyCreator.wrapIfNecessary() 会找到所有的Advisor传入ProxyFactory用于后面生成代理<br/>
-> AbstractAutoProxyCreator.createProxy  设置是JDK代理还是Cglib代理<br/> 
-> ProxyCreatorSupport.createAopProxy  创建代理 <br/>
-> DefaultAopProxyFactory.createAopProxy  方法中根据前面设置创建JdkDynamicAopProxy或者ObjenesisCglibAopProxy

### JDK 代理
JdkDynamicAopProxy.getProxy方法 JdkDynamicAopProxy实现InvocationHandler，将自己作为InvocationHandler用于JDK代理创建 <br/>

JdkDynamicAopProxy.invoke(Object proxy, Method method, Object[] args)

- AdvisedSupport.getInterceptorsAndDynamicInterceptionAdvice(Method method, @Nullable Class<?> targetClass) 
-> DefaultAdvisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(Advised config, Method method, @Nullable Class<?> targetClass)
根据方法匹配的切面信息，建立执行链
- new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain) 构建ReflectiveMethodInvocation对象
- invocation.proceed() 调用执行，方法里调用创建的执行链，执行链就是各个切面


### Cglib代理
ObjenesisCglibAopProxy.getProxy方法调用父类CglibAopProxy方法，创建Enhancers实例，用于后面创建代理 <br/>
-> ObjenesisCglibAopProxy.createProxyClassAndInstance <br/>
-> Enhancer.createClass <br/>
-> Enhancer.createHelper <br/>
-> AbstractClassGenerator.create <br/>
-> AbstractClassGenerator$ClassLoaderData.get 应该是缓存机制<br/>
-> LoadingCache.get 缓存，访问没有时加载,加载函数是在初始化AbstractClassGenerator$ClassLoaderData时候的一个匿名函数类 <br/>
-> LoadingCache.createEntry 调用传入的匿名内部类中的apply方法创建对象 <br/>

利用FutureTask异步创建对象
匿名Function中apply <br/>
Enhancer.generate(ClassLoaderData data) <br/>
AbstractClassGenerator.generate(ClassLoaderData data) <br/>
CglibAopProxy$ClassLoaderAwareUndeclaredThrowableStrategy.generate(ClassGenerator cg) <br/>
DefaultGeneratorStrategy.generate(ClassGenerator cg) 这个方法中最终会调用到Enhancer.generateClass方法，总体是利用ASM拼装出代理类的字节码，再通过反射
调用ClassLoader的方法实例化类<br/>
-> TransformingClassGenerator.generateClass <br/>
-> Enhancer.generateClass

Advice——AOP联盟定义的增强接口，增强包括横切逻辑和部分链接点信息（方法中哪一点加入横切逻辑的方位信息）
Pointcut——切点，定义增强织入哪个类哪个方法上
Advisor——切面，包括增强和切点

#### 切点类型  
- StaticMethodMatcherPointcut
- DynamicMethodMatcherPointcut
- AnnotationMatchingPointcut
- 


### SpringBoot启动

#### SpringApplication启动
- 根据classpath创建一个合适的ApplicationContext
- 注册一个CommandLinePropertySource将命令行参数添加到 spring properties
- 刷新application context，加载所有的单实例bean
- 触发CommandLineRunner bean

#### 刷新application context（AbstractApplicationContext#refresh)
##### AbstractApplicationContext#prepareRefresh()
设置启动时间和活跃标识，执行property sources的初始化
#####  AbstractApplicationContext#obtainFreshBeanFactory
告诉子类刷新bean工厂
#####  AbstractApplicationContext#prepareBeanFactory
配置工厂的标准上下文特性，例如上下文的类加载器和后处理器。配置类加载器，StandardBeanExpressionResolver（表达式解析器），ResourceEditorRegistrar（属性编辑
器）
##### AbstractApplicationContext#postProcessBeanFactory
#####  AbstractApplicationContext#invokeBeanFactoryPostProcessors方法  
1. 调用所有的BeanDefinitionRegistryPostProcessor.BeanDefinitionRegistryPostProcessor  
ConfigurationClassPostProcessor继承BeanDefinitionRegistryPostProcessor负责注册扫描路基下的Bean，加载BeanDefinition。
2. 调用所有的BeanFactoryPostProcessor.postProcessBeanFactory
##### AbstractApplicationContext#registerBeanPostProcessors
按顺序注册BeanPostProcessor，加载BeanPostProcessor类型bean实例
##### AbstractApplicationContext#initMessageSource
初始化MessageSource（参数化和国际化消息）
##### AbstractApplicationContext#initApplicationEventMulticaster
初始化ApplicationEventMulticaster
##### AbstractApplicationContext#onRefresh()
初始化特定的bean，用于子类扩展。ServletWebServerApplicationContext#onRefresh方法，用于创建内置tomcat相关的bean
##### AbstractApplicationContext#registerListeners
注册Listener
##### AbstractApplicationContext#finishBeanFactoryInitialization
初始化剩下所有的bean
1. DefaultListableBeanFactory#preInstantiateSingletons
-> AbstractAutowireCapableBeanFactory#createBean
--> AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation 执行InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation方法，某一个InstantiationAwareBeanPostProcessor
的postProcessBeforeInstantiation方法返回非空的bean（提供重写bean的）直接返回，剩余InstantiationAwareBeanPostProcessor方法不再执行，只执行BeanPostProcessor#postProcessAfterInitialization
--> AbstractAutowireCapableBeanFactory#doCreateBean
--->AbstractAutowireCapableBeanFactory#applyMergedBeanDefinitionPostProcessors
执行MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition方法
--->AbstractAutowireCapableBeanFactory#populateBean
执行所有InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation方法InstantiationAwareBeanPostProcessor#postProcessProperties方法
--->AbstractAutowireCapableBeanFactory#initializeBean
执行所有的BeanPostProcessor#postProcessBeforeInitialization和BeanPostProcessor#postProcessAfterInitialization方法




