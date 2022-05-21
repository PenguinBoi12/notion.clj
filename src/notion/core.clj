(ns notion.core
  (:require
    [clojure.edn :as edn]
    [notion.client :as client])
  (:gen-class))

(def client (client/init!))

; (defn -main [& args]
;   (let [user (client/user client "4175e406-b678-4e16-9988-c866583daaa6")]
;     (println user)))
(defn -main [& args]
  (let [db (client/database client "72fed03b27ca48278f41000c4920afe9")]
    (println db)))
(-main)
