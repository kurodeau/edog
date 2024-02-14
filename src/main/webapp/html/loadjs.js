// Function to create and append <script> element
const createScript = (src) => {
  return new Promise((resolve, reject) => {
    const script = document.createElement("script");
    script.src = src;
    script.onload = resolve; // Resolve the promise when the script is loaded
    script.onerror = reject; // Reject the promise if there's an error loading the script
    document.body.appendChild(script);
  });
};

const fetchConfig = fetch("config.json")
  .then((response) => response.json())
  .catch((error) => {
    console.error("Error fetching config:", error);
    throw error; // Propagate the error to the next .catch block
  });

fetchConfig
  .then((config) => {
    const resources = config.resources;
    const baseURL = config.baseURLServlet;

    // Create an array of promises for dynamic script loading
    const scriptPromises = [];

    // Function to check if script exists and create <script> element
    const checkAndCreateScript = (scriptPath) => {
      const fullPath = baseURL + scriptPath;

      // Create a promise for each script
      const scriptPromise = new Promise((resolve, reject) => {
        // Check if the script exists, if not, use the fallback base URL to reconstruct the path
        fetch(fullPath, { method: "HEAD" })
          .then((response) => {
            if (response.ok) {
              createScript(fullPath)
                .then(resolve) // Resolve the script promise when the script is loaded
                .catch(reject); // Reject the script promise if there's an error loading the script
            } else {
              console.log("VS Code development path for script:", fullPath);

              const newScriptPath = config.baseURLVSCode + scriptPath;
              createScript(newScriptPath).then(resolve).catch(reject);
            }
          })
          .catch((error) => {
            console.error("Error checking script:", fullPath, error);
            reject(error); // Reject the script promise if there's an error checking the script
          });
      });

      scriptPromises.push(scriptPromise); // Add the script promise to the array
    };

    // Dynamically create <script> elements for each vendor
    config.resources.js.forEach(checkAndCreateScript);

    const mainScriptPath = baseURL + config.resources.mainjs;

    // Create a promise for the main script
    const mainScriptPromise = new Promise((resolve, reject) => {
      // Check if the main script exists, if not, use the fallback base URL to reconstruct the path
      fetch(mainScriptPath, { method: "HEAD" })
        .then((response) => {
          if (response.ok) {
            createScript(mainScriptPath).then(resolve).catch(reject);
          } else {
            console.log(
              "VS Code development path for main script:",
              mainScriptPath
            );
            const newMainScriptPath =
              config.baseURLVSCode + config.resources.mainjs;
            createScript(newMainScriptPath).then(resolve).catch(reject);
          }
        })
        .catch((error) => {
          console.error("Error checking main script:", mainScriptPath, error);
          reject(error);
        });
    });

    scriptPromises.push(mainScriptPromise); // Add the main script promise to the array

    // Use Promise.all to wait for all script promises to resolve
    return Promise.all(scriptPromises);
  })
  .then(() => {
    console.log("All scripts loaded successfully");
    // Dispatch the event after all scripts have been successfully loaded
    document.dispatchEvent(new Event("loadjsCompleted"));
  })
  .catch((error) => {
    console.error("Unhandled error:", error);
  });
