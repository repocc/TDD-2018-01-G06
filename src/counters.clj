(ns counters)

(def counters-map (agent {}))

(defn inc-counter [counter-name]
  (send counters-map assoc counter-name (+ 1 (get @counters-map counter-name)))  
)

(defn counter-status [counter-name]
  (get @counters-map counter-name)  
)

;;mapa con el nombre y status del contador
(defn add-counter-to-map [counter-name cont] 
  (send counters-map assoc counter-name cont)
)

(defn counter-value [nam args]
  (counter-status nam)
)

(defn define-counter [nam param condition]

)

(defmulti current identity)
(defmethod current "spam" true)

