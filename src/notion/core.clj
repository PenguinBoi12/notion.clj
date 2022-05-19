(ns notion.core
  (:require
    [clojure.edn :as edn]
    [notion.client :as client])
  (:gen-class))

(def client (client/init!))

(defn -main [& args]
  (let [user (client/fetch :user client "4175e406-b678-4e16-9988-c866583daaa6")]
    (println (:name user))))
