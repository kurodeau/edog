// 函数用于创建和附加<link>元素
const createLink = (href, type) => {
  const link = document.createElement("link");

  link.rel = type;
  link.href = href;
  console.log(link);

  if (type === "icon") {
    console.log(link);
  }
  document.head.appendChild(link);
};

fetch("config.json")
  .then((response) => response.json())
  .then((config) => {
    const resources = config.resources;
    const baseURL = config.baseURLServlet;

    // 动态创建基于配置的<link>元素
    // createLink(baseURL + resources.favicon, "icon");
    for (const key in resources) {
      console.log(key);
    }
    resources.webfontspreconnect.forEach((font) =>
      createLink(font, "preconnect")
    );
    resources.webfonts.forEach((font) => createLink(font, "stylesheet"));

    const faviconPath = baseURL + resources.favicon;
    fetch(faviconPath, { method: "HEAD" })
      .then((response) => {
        if (response.ok) {
          // 如果 favicon 存在，則創建並附加<link>元素
          createLink(faviconPath, "icon");
        } else {
          // 如果 favicon 不存在，使用替代的基礎URL重新構建路徑
          console.log("VS Code 開發路徑 for favicon:", faviconPath);
          const newFaviconPath = config.baseURLVSCode + resources.favicon;

          // 創建並附加<link>元素
          createLink(newFaviconPath, "icon");
        }
      })
      .catch((error) => {
        console.error("Error checking favicon:", faviconPath, error);
      });

    resources.icons.forEach((icon) => {
      const iconPath = baseURL + icon;
      // 检查图标是否存在，如果不存在，则使用备用基础URL重新构建路径
      fetch(iconPath, { method: "HEAD" })
        .then((response) => {
          if (response.ok) {
            createLink(iconPath, "stylesheet");
          } else {
            console.log("VS Code 開發路徑 for icon:", iconPath);
            const newIconPath = config.baseURLVSCode + icon;
            createLink(newIconPath, "stylesheet");
          }
        })
        .catch((error) => {
          console.error("Error checking icon:", iconPath, error);
        });
    });

    for (const [vendor, path] of Object.entries(resources.vendors)) {
      const vendorPath = baseURL + path;
      // 检查供应商资源是否存在，如果不存在，则使用备用基础URL重新构建路径
      fetch(vendorPath, { method: "HEAD" })
        .then((response) => {
          if (response.ok) {
            createLink(vendorPath, "stylesheet");
          } else {
            console.log("VS Code 開發路徑 for vendor:", vendorPath);
            const newVendorPath = config.baseURLVSCode + path;
            createLink(newVendorPath, "stylesheet");
          }
        })
        .catch((error) => {
          console.error("Error checking vendor:", vendorPath, error);
        });
    }

    const mainStylePath = baseURL + resources.mainStyle;
    // 检查主样式是否存在，如果不存在，则使用备用基础URL重新构建路径
    fetch(mainStylePath, { method: "HEAD" })
      .then((response) => {
        if (response.ok) {
          createLink(mainStylePath, "stylesheet");
        } else {
          console.log("VS Code 開發路徑 for mainStyle:", mainStylePath);
          const newMainStylePath = config.baseURLVSCode + resources.mainStyle;
          createLink(newMainStylePath, "stylesheet");
        }
      })
      .catch((error) => {
        console.error("Error checking mainStyle:", mainStylePath, error);
      });
  })
  .catch((error) => {
    console.log(error);
  });
