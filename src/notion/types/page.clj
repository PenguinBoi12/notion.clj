(ns notion.types.page)

(defrecord Page [id created-time created-by-id last-edited-time last-edited-by-id
                 archived? icon cover properties parent-id parent-type url])

(defn make-page [id created-time created-by-id last-edited-time last-edited-by-id
                 archived? icon cover properties parent-id parent-type url]
  (->Page id created-time created-by-id last-edited-time last-edited-by-id
          archived? icon cover properties parent-id parent-type url))

(defn build-page [m]
  m)
