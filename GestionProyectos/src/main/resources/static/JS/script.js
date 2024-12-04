async function fetchProjectData() {
    try {
        const response = await fetch('/proyectos');
        if (!response.ok) {
            throw new Error('Error al obtener los datos');
        }
        const data = await response.json();
        return data._embedded.proyectoList;
    } catch (error) {
        console.error('Error al obtener los datos de los proyectos:', error);
        return [];
    }
}

async function renderOverallSuccessChart() {
    const projects = await fetchProjectData();

    const results = projects.reduce(
        (acc, project) => {
            const resultKey = project.porcentajeExito > 80 ? 'Success' : 'Failure';
            acc[resultKey] = (acc[resultKey] || 0) + 1;
            return acc;
        },
        { Success: 0, Failure: 0 }
    );

    const ctx = document.getElementById('overallSuccessChart').getContext('2d');

    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Success', 'Failure'],
            datasets: [{
                data: [results.Success, results.Failure],
                backgroundColor: ['#007bff', '#d1e7ff'],
                hoverBackgroundColor: ['#0056b3', '#a3d0ff']
            }]
        },
        options: {
            plugins: {
                tooltip: { enabled: false }
            },
            events: []
        }
    });
}

renderOverallSuccessChart();
