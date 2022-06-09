(ns user.core
  (:require notion.client :as client))

(defn input [message]
  (print message)
  (flush)
  (read-line))

(defn show-user [id]
  (let [user (client/user client id)]
    (println user)))

(defn -main [& args]
  (def client (client/init!))

  (let [id (input "Enter a user id: ")]
    (show-user id)))
