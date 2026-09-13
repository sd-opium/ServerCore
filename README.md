# ServerCore

Ein modular aufgebautes Minecraft-Paper-Plugin mit mehreren typischen Server-Systemen.

## Enthalten
- Spawn-System
- Homes
- Warps
- TPA-Anfragen
- Kits
- Admin-Freeze
- einfache Chatformatierung
- Join/Quit-Nachrichten
- GUI-Menü
- persistente YAML-Dateien
- getrennte Manager, Commands, Listener und Utilities

## Befehle
- `/spawn`
- `/setspawn`
- `/home [name]`
- `/sethome [name]`
- `/delhome [name]`
- `/homes`
- `/warp <name>`
- `/setwarp <name>`
- `/delwarp <name>`
- `/warps`
- `/tpa <spieler>`
- `/tpaccept`
- `/tpdeny`
- `/kit <name>`
- `/kits`
- `/servermenu`
- `/sc reload`

## Permissions
- `servercore.admin`
- `servercore.setspawn`
- `servercore.setwarp`
- `servercore.kits.*`
- `servercore.freeze`

## Bauen
Java 21 + Maven:
```bash
mvn clean package
```

Die fertige JAR befindet sich anschließend in `target/`.
