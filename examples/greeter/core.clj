(ns greeter.core
  (:require
    [notion.client :as client]
    [notion.api.user :as user]))

(defn greet-user [user]
  "Greets the given user"
  (println "Hello" (:name user)))

(defn -main [& args]
  (let [client (client/init!)
        user   (user/find "4175e406b6784e169988c866583daaa6")]
    (greet user)))