(ns notion.client
  (:require
    [notion.types.client :as client]
    [notion.api.user :refer :all])
  (:import [java.io IOException]))

; This module will contain the logics to make the basic calls to the users,
; blocks, pages, search, etc.

; For now, the only attribute of a configuration record is the connection token.
; Later, we might want to add other configuration, socket, cache, etc.
(defonce ^:private default-config-path "resources/config.edn")

(defn load-config [filename]
  (try
    (edn/read-string (slurp filename))
    (catch IOException e
      (throw (ex-info "Can't load the configuration!" {:file filename})))))

  (defn init! [& config-path]
    (let [config (load-config default-config-path)]
      (client/->Client config)))
