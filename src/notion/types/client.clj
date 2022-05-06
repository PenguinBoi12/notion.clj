(ns notion.types.client)

(defprotocol ClientProtocol
  (token [this]))

(defrecord Client [config]
  ClientProtocol
  (token [_] (:token config)))
