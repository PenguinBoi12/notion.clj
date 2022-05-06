; I want to make a notion/github bot that will listen for new issues assinged to
; me from X repo (In my case it would be for Nagano first). The smart thing would
; probably be to use github webhooks with the notion integration we already have.

; When a new issue is assigned, we add a new element to a kanban datatabase. We
; Set the titles, links and that's all. The rest is to be set manually.

; token expires in 30 days - 12/04/2022
; curl -i -u PenguinboiBoi12:$token https://api.github.com/repos/semiweb/nagano/issues
(ns notion.core
  (:require
    [clojure.edn :as edn]
    [notion.client :as client]
    [notion.api.user :as user])
  (:gen-class))

(defn -main [& args]
  (let [client (client/init!)
        user (user/get-user client "4175e406-b678-4e16-9988-c866583daaa6")]
    (:name user)))
