; I want to make a notion/github bot that will listen for new issues assinged to
; me from X repo (In my case it would be for Nagano first). The smart thing would
; probably be to use github webhooks with the notion integration we already have.

; When a new issue is assigned, we add a new element to a kanban datatabase. We
; Set the titles, links and that's all. The rest is to be set manually.

; token expires in 30 days - 12/04/2022
; curl -i -u PenguinboiBoi12:$token https://api.github.com/repos/semiweb/nagano/issues
(ns notion.core
  (:require
    [notion.notion :refer [init-bot]]
    [notion.api.user :as user])
  (:gen-class))

(defn display-users [notion-bot]
  (loop [users (user/get-users notion-bot)]
    (when-let [user (first (seq users))]
      (println user)-
      (recur (rest users)))))

; (deftype Response [notion-bot code raw])
;
; (deftype User [id name avatar-url type type-info response])
;
; (defn make-user [response]
;   (let [raw-user (.raw response)]
;     (->User (:id raw-user)
;             (:name raw-user)
;             (:avatar-url raw-user)
;             (:type raw-user)
;             (:type-info raw-user)
;             response)))

; (defn -main [& args]
;   (let [notion-bot (init-bot)
;         raw-user (get-user notion-bot "4f1f6522-00ea-4e37-9ffc-0477a302c4c1")
;         user (make-user (->Response notion-bot 200 raw-user))]
;
;     (println (.name user))))

(defn -main [& args]
  (def notion-bot (init-bot))

  (display-users notion-bot))
