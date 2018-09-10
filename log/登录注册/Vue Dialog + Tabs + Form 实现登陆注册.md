## Vue Dialog + Tabs + Form 实现登陆注册

一开始始终无法自动绑定默认 Tab 为登陆。问题在于生命周期。

activeName 的值应该在 created() 中设置

