(ns greeter.core
  (:require
    [notion.client :as client]
    [notion.api.user :as user]))

(defn greet-user [user]
  "Greets the given user"
  (println "Hello" (:name user)))

(defn -main [& args]
  (let [client (client/init!)
        user   (user/me client)]
    (greet user)))
