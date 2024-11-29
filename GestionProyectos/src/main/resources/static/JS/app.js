async function fetchData() {
    const response = await fetch('datos.json');
    const data = await response.json();
    return data;
}

async function generateGraphs() {
    const data = await fetchData();
    const technologies = data.Tecnologias;
    const projects = data.Proyectos;
    const relations = data.Proyectos_Tecnologias;

    const container = document.getElementById('graficasTecnologias');

    technologies.forEach(technology => {
        const relatedProjects = relations
            .filter(rel => rel.id_tecnologia === technology.id)
            .map(rel => projects.find(proj => proj.id === rel.id_proyecto));

        const results = relatedProjects.reduce(
            (acc, project) => {
                if (project) {
                    const resultKey = project.resultado === "Exito" ? "Success" : "Failure";
                    acc[resultKey] = (acc[resultKey] || 0) + 1;
                }
                return acc;
            },
            { Success: 0, Failure: 0 }
        );

        const col = document.createElement('div');
        col.className = "col-lg-4 col-md-6";

        const card = document.createElement('div');
        card.className = "card";

        const canvas = document.createElement('canvas');
        canvas.id = `chartTechnology_${technology.id}`;
        card.appendChild(canvas);

        const title = document.createElement('h5');
        title.textContent = `Results of ${technology.nombre}`;
        card.appendChild(title);

        col.appendChild(card);
        container.appendChild(col);

        new Chart(canvas.getContext('2d'), {
            type: 'pie',
            data: {
                labels: ['Success', 'Failure'],
                datasets: [
                    {
                        data: [results.Success, results.Failure],
                        backgroundColor: ['#007bff', '#d1e7ff'],
                        hoverBackgroundColor: ['#0056b3', '#a3d0ff']
                    }
                ]
            },
            options: {
                plugins: {
                    tooltip: { enabled: false } // Disable tooltips
                },
                events: [] // Disable all interactions
            }
        });
    });
}

generateGraphs();
