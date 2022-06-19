(ns greeter
  (:require [notion.client :as client]))

(defn greet-user [user]
  (let [username (:name user)]
    (println "Hello" username)))

(defn -main [& args]
  (let [client (client/init!),
        user (client/user client "4175e406b6784e169988c866583daaa6")]
    (greet-user user)))
