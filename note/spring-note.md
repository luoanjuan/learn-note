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
