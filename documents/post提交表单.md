要向Spring Boot接口提交表单数据（Form Data），你可以使用`POST`请求，并确保请求的`Content-Type`设置为`application/x-www-form-urlencoded`。Spring Boot会自动解析表单数据，并将其映射到方法参数或对象中。

以下是一个简单的示例，展示了如何在Spring Boot中接收和处理表单数据：

1. **创建表单页面（HTML）**：首先，你需要创建一个HTML表单页面，用于提交数据。假设你有一个名为`index.html`的表单页面：

```html
<!DOCTYPE html>
<html>
<head>
    <title>Form Submission</title>
</head>
<body>
    <form action="/submit" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
```

2. **创建Spring Boot Controller**：接下来，你需要创建一个Spring Boot控制器，来处理表单提交和数据处理。

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/")
    public String showForm() {
        return "index"; // 返回表单页面的名称
    }

    @PostMapping("/submit")
    public String processForm(@RequestParam String name, @RequestParam String email) {
        // 在这里处理提交的表单数据，你可以调用服务层或执行其他逻辑
        System.out.println("Received Name: " + name);
        System.out.println("Received Email: " + email);
        
        // 可以返回一个结果页面或重定向到其他页面
        return "result"; // 返回结果页面的名称
    }
}
```

在上述代码中，`showForm()`方法用于显示表单页面，`processForm()`方法用于处理表单数据提交。`@RequestParam`注解用于从请求参数中获取数据。

3. **配置视图解析器**：确保在Spring Boot应用的配置中配置了视图解析器，以便正确识别视图名称。

这就是一个简单的示例，展示了如何在Spring Boot中接收和处理表单数据。根据实际需求，你可以在`processForm()`方法中添加逻辑来处理提交的数据。